package com.gtt.gttcore.data.molecule;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
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
                if (atom.isInvisible()) return moleculeElement;
                int connectionCount = 0;
                for (MoleculeElement<?> content : molecule.contents()) {
                    if (content instanceof Bond bond){
                        if (bond.a() == atom.index() || bond.b() == atom.index()){
                            connectionCount += bond.lines().length;
                        }
                    }
                }
                Material material = atom.element().element().material;
                int hydrogenCount = 0;
                if (material != GTMaterials.NULL) {
                    int protonCount = (int) material.getElement().protons();
                    hydrogenCount = getMaxValence(protonCount) - connectionCount;
                    LOGGER.info("Element {} Hydrogen Count {}", atom.element().element().symbol, hydrogenCount);
                }
                return new Atom(atom.index(), atom.element(), Optional.empty(), Optional.ofNullable(hydrogenCount <= 0 ? null : Element.H.count(hydrogenCount)), Optional.empty(), Optional.empty(), atom.position());
            }
            return moleculeElement;
        }).collect(Collectors.toList()));
        return result;

    }
    public static int getMaxValence(int protonCount) {
        return switch (protonCount) {
            case 1 -> 1;    // H
            case 5 -> 3;    // B
            case 6 -> 4;    // C
            case 7 -> 3;    // N
            case 8 -> 2;    // O
            case 9 -> 1;    // F
            case 13 -> 3;    // Al
            case 14 -> 4;    // Si
            case 15 -> 3;  // P
            case 16 -> 2;    // S
            case 17 -> 1;    // Cl
            case 33 -> 3;    // As
            case 34 -> 2;    // Se
            case 35 -> 1;    // Br
            case 52 -> 2;    // Te
            case 53 -> 1;    // I
            default -> 0;
        };
    }
}
