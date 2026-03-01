package com.gtt.gttcore.data.molecule;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gtt.gttcore.GTTCore;
import com.rubenverg.moldraw.molecule.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class MolfileToMolecule {
    public static void run(ExistingFileHelper existingFileHelper, BiConsumer<ResourceLocation, Molecule> provider) {
        existingFileHelper.getManager(PackType.CLIENT_RESOURCES).listResources("molecules", r -> r.getNamespace().equals(GTTCore.MOD_ID) && r.getPath().endsWith(".mol")).forEach((rl, resource) -> {
            String path = rl.getPath();
            String childName = path.substring(path.lastIndexOf("/") + 1).replaceAll(".mol", "");
            LOGGER.info("Generating Molfile {}", childName);
            try {
                BufferedReader reader = resource.openAsReader();
                int dotPos = childName.lastIndexOf(".");
                if (dotPos >= 0) {
                    childName = childName.substring(0, dotPos);
                }
                provider.accept(GTTCore.id(childName), toJson(reader));
                reader.close();
            } catch (IOException e){
                LOGGER.error(e);
            }
        });
    }
    public static Molecule toJson(BufferedReader reader) throws IOException {
        reader.readLine();
        reader.readLine();
        reader.readLine();
        String head = reader.readLine();
        String[] headSplitted = head.strip().split("\\s+");
        int atomCount = Integer.parseInt(headSplitted[0]);
        int bondCount = Integer.parseInt(headSplitted[1]);
        if (!Objects.equals(headSplitted[10], "V2000")) throw new IOException("Molfiles with version other than V2000 are not supported");
        Molecule molecule = new Molecule().xy();
        try {
            String body;
            for (int i = 0; i < atomCount; i++) {
                body = reader.readLine();
                String[] bodySplitted = body.strip().split("\\s+");
                float x = Float.parseFloat(bodySplitted[0]);
                float y = Float.parseFloat(bodySplitted[1]);
                String element = bodySplitted[3];
                if (Objects.equals(element, "C")) {
                    molecule.invAtom(x, y);
                }
                else {
                    molecule.atom(Element.elements.get(element), x, y);
                }
            }
            for (int i = 0; i < bondCount; i++) {
                body = reader.readLine();
                String[] bodySplitted = body.strip().split("\\s+");
                int i1 = Integer.parseInt(bodySplitted[0]);
                int i2 = Integer.parseInt(bodySplitted[1]);
                int type = Integer.parseInt(bodySplitted[2]);
                Bond.Line[] lines = switch (type){
                    case 1, 4 -> Bond.SINGLE;
                    case 2 -> Bond.DOUBLE;
                    case 3 -> Bond.TRIPLE;
                    default -> new Bond.Line[0];
                };
                molecule.bond(i1 - 1, i2 - 1, lines);
            }
        }
        catch (IOException ignored){

        }
        Molecule result = new Molecule();
        result.addAll(molecule.contents().stream().map(moleculeElement -> {
            if (moleculeElement instanceof Atom atom){
                int connectionCount = 0;
                for (MoleculeElement<?> content : molecule.contents()) {
                    if (content instanceof Bond bond){
                        if (bond.a() == atom.index() || bond.b() == atom.index()){
                            connectionCount += 1;
                        }
                    }
                }
                Material material = atom.element().element().material;
                int hydrogenCount = 0;
                if (material != GTMaterials.NULL) {
                     hydrogenCount = calculateValence(
                            (int) material.getElement().protons(),
                            connectionCount
                    ).hydrogenCount;
                    LOGGER.info("Element {} Hydrogen Count {}", atom.element().element().symbol, hydrogenCount);
                }
                return new Atom(atom.index(), atom.element(), Optional.empty(), Optional.ofNullable(hydrogenCount <= 0 ? null : Element.H.count(hydrogenCount)), Optional.empty(), Optional.empty(), atom.position());
            }
            return moleculeElement;
        }).collect(Collectors.toList()));
        return molecule;

    }

    public static ValenceCalculationResult calculateValence(int protonCount, int connectionCount){
        return switch (calculateGroup(protonCount)){
            case 1 -> new ValenceCalculationResult(1, 1 - connectionCount);
            case 2 -> {
                if (connectionCount == 0 || connectionCount == 2) yield new ValenceCalculationResult(2, 0);
                yield new ValenceCalculationResult(connectionCount, -1);
            }
            case 3 -> {
                if (
                        protonCount == GTElements.B.protons() ||
                        protonCount == GTElements.Al.protons() ||
                        protonCount == GTElements.Ga.protons() ||
                        protonCount == GTElements.In.protons()
                ){
                    yield new ValenceCalculationResult(3, 3 - connectionCount);
                } else if (protonCount == GTElements.Tl.protons()) {
                    if (connectionCount <= 1){
                        yield new ValenceCalculationResult(1, 1 - connectionCount);
                    }else {
                        yield new ValenceCalculationResult(3, 3 - connectionCount);
                    }
                }
                yield new ValenceCalculationResult(connectionCount, 0);
            }
            case 4 -> {
                if (
                        protonCount == GTElements.C.protons() ||
                        protonCount == GTElements.Si.protons() ||
                        protonCount == GTElements.Ge.protons()
                ){
                    yield new ValenceCalculationResult(4, 4 - connectionCount);
                } else if (
                        protonCount == GTElements.Sn.protons() ||
                        protonCount == GTElements.Pb.protons()
                ) {
                    if (connectionCount <= 2){
                        yield new ValenceCalculationResult(2, 2 - connectionCount);
                    } else {
                        yield new ValenceCalculationResult(4, 4 - connectionCount);
                    }
                }
                yield new ValenceCalculationResult(connectionCount, 0);
            }
            case 5 -> {
                if (
                        protonCount == GTElements.N.protons() ||
                        protonCount == GTElements.P.protons()
                ){
                    if (protonCount == GTElements.N.protons() || connectionCount <= 3){
                        yield new ValenceCalculationResult(3, 3 - connectionCount);
                    }
                    else {
                        yield new ValenceCalculationResult(5, 5 - connectionCount);
                    }
                }
                else if (
                        protonCount == GTElements.Bi.protons() ||
                        protonCount == GTElements.Sb.protons() ||
                        protonCount == GTElements.As.protons()
                ) {
                    if (connectionCount <= 3 && protonCount != GTElements.As.protons()){
                        yield new ValenceCalculationResult(3, 3 - connectionCount);
                    } else {
                        yield new ValenceCalculationResult(5, 5 - connectionCount);
                    }
                } else {
                    yield new ValenceCalculationResult(connectionCount, 0);
                }
            }
            case 6 -> {
                if (protonCount == GTElements.O.protons()){
                    yield new ValenceCalculationResult(2, 2 - connectionCount);
                } else if (
                        protonCount == GTElements.S.protons() ||
                        protonCount == GTElements.Se.protons() ||
                        protonCount == GTElements.Po.protons()
                ) {
                    if (connectionCount <= 2){
                        yield new ValenceCalculationResult(2, 2 - connectionCount);
                    } else if (connectionCount <= 4){
                        yield new ValenceCalculationResult(4, 4 - connectionCount);
                    } else {
                        yield new ValenceCalculationResult(6, 6 - connectionCount);
                    }
                } else if (protonCount == GTElements.Te.protons()) {
                    if (connectionCount <= 2) {
                        yield new ValenceCalculationResult(2, 2 - connectionCount);
                    } else if (connectionCount <= 4) {
                        yield new ValenceCalculationResult(4, 4 - connectionCount);
                    } else if (connectionCount <= 6) {
                        yield new ValenceCalculationResult(6, 6 - connectionCount);
                    } else {
                        yield new ValenceCalculationResult(connectionCount, -1);
                    }
                }
                yield new ValenceCalculationResult(connectionCount, 0);
            }
            case 7 -> {
                if (protonCount == GTElements.F.protons()) {
                    yield new ValenceCalculationResult(1, 1 - connectionCount);
                } else if (
                        protonCount == GTElements.Cl.protons() ||
                        protonCount == GTElements.Br.protons() ||
                        protonCount == GTElements.I.protons() ||
                        protonCount == GTElements.At.protons()
                ) {
                    if (connectionCount <= 1) {
                        yield new ValenceCalculationResult(1, 1 - connectionCount);
                    }
                    if (
                            connectionCount == 2 ||
                            connectionCount == 4 ||
                            connectionCount == 6
                    ) {
                        yield new ValenceCalculationResult(connectionCount, -1);
                    }
                    if (connectionCount > 7) {
                        yield new ValenceCalculationResult(connectionCount, -1);
                    }
                }
                yield new ValenceCalculationResult(connectionCount, 0);
            }
            case 8 -> {
                if (protonCount == GTElements.Pt.protons()) {
                    if (connectionCount <= 2) {
                        yield new ValenceCalculationResult(2, 2 - connectionCount);
                    }
                    if (connectionCount <= 4) {
                        yield new ValenceCalculationResult(4, 4 - connectionCount);
                    }
                    yield new ValenceCalculationResult(connectionCount, -1);
                }
                if (connectionCount == 0) {
                    yield new ValenceCalculationResult(1, 0);
                }
                yield new ValenceCalculationResult(connectionCount, -1);
            }
            default -> throw new IllegalStateException("Unexpected group value: " + calculateGroup(protonCount));
        };
    }

    public static int calculateGroup(int protonCount){
        if (protonCount <= 2){
            return protonCount <= 1 ? 1 : 8;
        } else if (protonCount <= 18) {
            return (protonCount - 3) % 8 + 1;
        } else if (protonCount <= 54) {
            return (protonCount - 2) % 18 < 8 ? Math.min((protonCount - 2) % 18 + 1, 8) : (protonCount - 11) % 18 + 1;
        } else if (protonCount <= 118) {
            return (protonCount - 23) % 32 + 1 < 3 ? (protonCount - 23) % 32 + 1 :
                    (protonCount - 23) % 32 + 1 < 18 ? 3 :
                    ((protonCount - 23) % 32 + 1 >= 25 ? Math.min((protonCount - 4) % 32 + 1, 8) : (protonCount - 15) % 32 + 1);
        }
        return 1;
    }

    public record ValenceCalculationResult(int valence, int hydrogenCount){

    }
}
