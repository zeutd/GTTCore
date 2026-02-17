package com.gtt.gttcore.data;

import com.gtt.gttcore.GTTCore;
import com.rubenverg.moldraw.molecule.Bond;
import com.rubenverg.moldraw.molecule.Element;
import com.rubenverg.moldraw.molecule.Molecule;
import net.minecraft.resources.ResourceLocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.BiConsumer;

public class MolfileToMolecule {
    public static void run(BiConsumer<ResourceLocation, Molecule> provider) {
        File file = new File("../src/main/resources/assets/gttcore/molecules");
        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                String childName = child.getName();
                GTTCore.LOGGER.info("Generating Molfile {}", childName);
                try {
                    provider.accept(GTTCore.id(childName.substring(0, childName.lastIndexOf("."))), toJson(child));

                } catch (IOException e){
                    GTTCore.LOGGER.error(e);
                }
            }
        }
    }
    public static Molecule toJson(File file) throws IOException {
        if (!file.getName().endsWith(".mol")) throw new IOException("The file is not a type of .mol");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        reader.readLine();
        reader.readLine();
        String head = reader.readLine();
        String[] headSplitted = head.strip().split("\\s+");
        int atomCount = Integer.parseInt(headSplitted[0]);
        int bondCount = Integer.parseInt(headSplitted[1]);
        GTTCore.LOGGER.debug(headSplitted[10]);
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
//        for (MoleculeElement<?> moleculeElement : molecule.contents())
//        {
//            if (moleculeElement instanceof Atom atom){
//                if (atom.element().element().material == GTMaterials.NULL) continue;
//                int connectedCount = 0;
//                for (MoleculeElement<?> moleculeElement2 : molecule.contents()) {
//                    if (moleculeElement2 instanceof Bond bond){
//                        connectedCount++;
//                    }
//                }
//                //atom.replaceInOrder()caculateValence((int) atom.element().element().material.getElement().protons(), connectedCount);
//            }
//        };
        return molecule;

    }

//    public static ValenceCalculationResult caculateValence(int protonCount, int connectionCount){
//        return switch (caculateGroup(protonCount)){
//            case 1 -> new ValenceCalculationResult(1, 1 - connectionCount);
//            case 2 -> {
//                if (connectionCount == 0 || connectionCount == 2) yield new ValenceCalculationResult(2, 0);;
//            }
//            case 3 -> 1;
//            case 4 -> 1;
//            case 5 -> 1;
//            case 6 -> 1;
//            case 7 -> 1;
//            case 8 -> 1;
//            default -> throw new IllegalStateException("Unexpected value: " + caculateGroup(protonCount));
//        };
//    }
//
//    public static int caculateGroup(int protonCount){
//        if (protonCount <= 2){
//            return protonCount <= 1 ? 1 : 8;
//        } else if (protonCount <= 18) {
//            return (protonCount - 3) % 8 + 1;
//        } else if (protonCount <= 54) {
//            return (protonCount - 2) % 18 < 8 ? Math.min((protonCount - 2) % 18 + 1, 8) : (protonCount - 11) % 18 + 1;
//        } else if (protonCount <= 118) {
//            return (protonCount - 23) % 32 + 1 < 3 ? (protonCount - 23) % 32 + 1 :
//                    (protonCount - 23) % 32 + 1 < 18 ? 3 :
//                    ((protonCount - 23) % 32 + 1 >= 25 ? Math.min((protonCount - 4) % 32 + 1, 8) : (protonCount - 15) % 32 + 1);
//        }
//        return 1;
//    }
//
//    public record ValenceCalculationResult(int valence, int hydrogenCount){
//
//    }
}
