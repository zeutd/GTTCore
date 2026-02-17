package com.gtt.gttcore.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gtt.gttcore.GTTCore;
import com.rubenverg.moldraw.molecule.Bond;
import com.rubenverg.moldraw.molecule.Element;
import com.rubenverg.moldraw.molecule.Molecule;
import com.rubenverg.moldraw.molecule.Parens;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class GTTMoleculesData {
    public static Map<ResourceLocation, Molecule> molecules() {
        Map<ResourceLocation, Molecule> molecules = new HashMap<>();
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
                .bond(8, 3)
                .bond(9, 4)
                .add(Parens.polymer(0, 1, 2, 3, 4, 5, 6))
        );
        molecules.put(GTCEu.id("ultra_high_molecule_weight_polyethylene"), new Molecule()
                .uv()
                .invAtom(0, 0)
                .invAtom(1, 0)
                .invAtom(1, 1)
                .invAtom(2, 1)
                .bond(0, 1)
                .bond(1, 2)
                .bond(2, 3)
                .add(Parens.polymer(1, 2)));
        return molecules;
    }
}
