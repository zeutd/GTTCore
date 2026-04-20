package com.gtt.gttcore.common.pipelike.particle;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticlePipeProperties {

    public final int throughput = 200;

    public static final ParticlePipeProperties INSTANCE = new ParticlePipeProperties();
}
