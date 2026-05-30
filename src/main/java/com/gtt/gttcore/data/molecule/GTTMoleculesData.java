package com.gtt.gttcore.data.molecule;

import com.gtt.gttcore.GTTCore;
import com.rubenverg.moldraw.molecule.Bond;
import com.rubenverg.moldraw.molecule.Element;
import com.rubenverg.moldraw.molecule.Molecule;
import com.rubenverg.moldraw.molecule.Parens;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class GTTMoleculesData {
    public static Molecule tetragonalNullable(Element center, @Nullable Element top, @Nullable Element back, @Nullable Element front, @Nullable Element side) {
        int index = 0;
        Molecule molecule = new Molecule()
                .xy()
                .atom(center, 0.0F, 0.0F);
        if (top != null) {
            index++;
            molecule
                    .atom(top, 0.0F, 1.0F)
                    .bond(0, index);
        }
        if (back != null) {
            index++;
            molecule
                    .atom(back, (float) Math.cos(Math.toRadians(-15.0)), (float) Math.sin(Math.toRadians(-15.0)))
                    .bond(0, index, Bond.Line.INWARD);
        }
        if (top != null) {
            index++;
            molecule
                    .atom(front, (float) Math.cos(Math.toRadians(-60.0)), (float) Math.sin(Math.toRadians(-60.0)))
                    .bond(0, index, Bond.Line.OUTWARD);
        }
        if (top != null) {
            index++;
            molecule
                    .atom(side, (float)Math.cos(Math.toRadians(-150.0)), (float)Math.sin(Math.toRadians(-150.0)))
                    .bond(0, index);
        }
        return molecule;
    }
    public static Map<ResourceLocation, Molecule> molecules() {
        Map<ResourceLocation, Molecule> molecules = new HashMap<>();
        molecules.put(GTTCore.id("silane"), Molecule.tetragonal(
                Element.Si,
                Element.H,
                Element.H,
                Element.H,
                Element.H
        ));
        molecules.put(GTTCore.id("silicon_chloride"), Molecule.tetragonal(
                Element.Si,
                Element.Cl,
                Element.Cl,
                Element.Cl,
                Element.Cl
        ));
        molecules.put(GTTCore.id("trichlorosilane"), Molecule.tetragonal(
                Element.Si,
                Element.H,
                Element.Cl,
                Element.Cl,
                Element.Cl
        ));
        molecules.put(GTTCore.id("phosphine"), tetragonalNullable(
                Element.P,
                null,
                Element.H,
                Element.H,
                Element.H
        ));
        molecules.put(GTTCore.id("arsenic_hydride"), tetragonalNullable(
                Element.As,
                null,
                Element.H,
                Element.H,
                Element.H
        ));
        molecules.put(GTTCore.id("trimethylindium"), tetragonalNullable(
                Element.In,
                null,
                Element.INVISIBLE,
                Element.INVISIBLE,
                Element.INVISIBLE
        ));
        molecules.put(GTTCore.id("trimethylgallium"), tetragonalNullable(
                Element.Ga,
                null,
                Element.INVISIBLE,
                Element.INVISIBLE,
                Element.INVISIBLE
        ));
        molecules.put(GTTCore.id("trimethylaluminium"), tetragonalNullable(
                Element.Al,
                null,
                Element.INVISIBLE,
                Element.INVISIBLE,
                Element.INVISIBLE
        ));
        molecules.put(GTTCore.id("iodomethane"), tetragonalNullable(
                Element.C,
                Element.I,
                Element.H,
                Element.H,
                Element.H
        ));



        molecules.put(GTTCore.id("phenolic_resin"), new Molecule()
                .uv()
                .invAtom(0.0F, 0.0F)
                .invAtom(-1.0F, 1.0F)
                .invAtom(-1.0F, 2.0F)
                .invAtom(0.0F, 2.0F)
                .invAtom(1.0F, 1.0F)
                .invAtom(1.0F, 0.0F)
                .atom(Element.O.one(), null, Element.H.one(), null, null, 2f, -1f)
                .invAtom(0, -1)
                .invAtom(2, 1)
                .invAtom(0, 3)
                .bond(0, 1)
                .bond(1, 2, Bond.DOUBLE)
                .bond(2, 3)
                .bond(3, 4, Bond.DOUBLE)
                .bond(4, 5)
                .bond(5, 0, Bond.DOUBLE)
                .bond(5, 6)
                .bond(7, 0)
                .bond(8, 4)
                .bond(9, 3)
                .add(Parens.polymer(0, 1, 2, 3, 4, 5, 6))
        );
        molecules.put(GTTCore.id("ultra_high_molecular_weight_polyethylene"), new Molecule()
                .uv()
                .invAtom(0, 0)
                .invAtom(1, 0)
                .invAtom(1, 1)
                .invAtom(2, 1)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
                .add(Parens.polymer(1, 2)));
        molecules.put(GTTCore.id("bromobutane"), new Molecule()
                .uv()
                .atom(Element.Br, 0, 0)
                .invAtom(1, 0)
                .invAtom(1, 1)
                .invAtom(2, 1)
                .invAtom(2, 2)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
                .bond(3, 4)
        );
        molecules.put(GTTCore.id("butanol"), new Molecule()
                .uv()
                .atom(Element.O.one(), null, Element.H.one() , null, null, 0, 0)
                .invAtom(1, 0)
                .invAtom(1, 1)
                .invAtom(2, 1)
                .invAtom(2, 2)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
                .bond(3, 4)
        );
        molecules.put(GTTCore.id("octanol"), new Molecule()
                .uv()
                .atom(Element.O.one(), null, Element.H.one() , null, null, 0, 0)
                .invAtom(1, 0)
                .invAtom(1, 1)
                .invAtom(2, 1)
                .invAtom(2, 2)
                .invAtom(3, 2)
                .invAtom(3, 3)
                .invAtom(4, 3)
                .invAtom(4, 4)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
                .bond(3, 4)
                .bond(4, 5)
                .bond(5, 6)
                .bond(6, 7)
                .bond(7, 8)
        );
        molecules.put(GTTCore.id("phthaloyl_chlorine"), new Molecule()
                .uv()
                .invAtom(0, 0)
                .invAtom(-1, 1)
                .invAtom(-1, 2)
                .invAtom(0, 2)
                .invAtom(1, 1)
                .invAtom(1, 0)
                .invAtom(2, 1)
                .atom(Element.Cl, 2, 2)
                .atom(Element.O, 3, 0)
                .invAtom(1, 0)
                .atom(Element.Cl, 1, 3)
                .atom(Element.O, -1, 4)
                .bond(0, 1)
                .bond(1, 2, Bond.DOUBLE)
                .bond(2, 3)
                .bond(3, 4, Bond.DOUBLE)
                .bond(4, 5)
                .bond(5, 0, Bond.DOUBLE)
                .bond(4, 6)
                .bond(6, 7)
                .bond(6, 8, Bond.DOUBLE)
                .bond(3, 9)
                .bond(9, 10)
                .bond(9, 11, Bond.DOUBLE)
        );
        molecules.put(GTTCore.id("oxalic_acid"), new Molecule()
                .uv()
                .atom(Element.H.one(), null, Element.O.one(), null, null, 0, 0)
                .invAtom(0, 1)
                .invAtom(1, 1)
                .atom(Element.O.one(), null, Element.H.one(), null, null, 1, 2)
                .atom(Element.O, -1, 2)
                .atom(Element.O, 2, 0)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
                .bond(1, 4, Bond.DOUBLE)
                .bond(2, 5, Bond.DOUBLE)
        );
        molecules.put(GTTCore.id("ethylene_glycol"), new Molecule()
                .uv()
                .atom(Element.H.one(), null, Element.O.one(), null, null, 0, 0)
                .invAtom(0, 1)
                .invAtom(1, 1)
                .atom(Element.O.one(), null, Element.H.one(), null, null, 1, 2)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
        );
        molecules.put(GTTCore.id("ethylene_oxide"), new Molecule()
                .uv()
                .invAtom(0, 0)
                .invAtom(1, 0)
                .atom(Element.O, 1, -1)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 0)
        );
        return molecules;
    }
}
