package com.gtt.gttcore.common.data;

import com.gtt.gttcore.api.DysonWorldSavedData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;
import static net.minecraft.commands.Commands.LEVEL_ADMINS;
import static net.minecraft.commands.Commands.literal;

public class GTTCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
        dispatcher.register(
                literal("gttcore")
                        .then(literal("dyson")
                                .requires(ctx -> ctx.hasPermission(LEVEL_ADMINS))
                                .then(literal("cloud")
                                        .then(literal("add").then(Commands.argument("amount", IntegerArgumentType.integer())
                                                    .executes(context -> {
                                                        DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonClouds += context.getArgument("amount", Integer.class);
                                                        context.getSource().sendSuccess(() ->
                                                                Component.translatable("gttcore.dyson_cloud.set",
                                                                        DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonClouds
                                                                ), true);
                                                        return SINGLE_SUCCESS;
                                                    })
                                                )
                                        )
                                        .then(literal("set").then(Commands.argument("amount", IntegerArgumentType.integer())
                                                        .executes(context -> {
                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonClouds = context.getArgument("amount", Integer.class);
                                                            context.getSource().sendSuccess(() ->
                                                                    Component.translatable("gttcore.dyson_cloud.set",
                                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonClouds
                                                                    ), true);
                                                            return SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.translatable("gttcore.dyson_cloud.query",
                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonClouds
                                                    ), true);
                                            return SINGLE_SUCCESS;
                                        })
                                )
                                .then(literal("sphere_joint")
                                        .then(literal("add").then(Commands.argument("amount", IntegerArgumentType.integer())
                                                        .executes(context -> {
                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonSphereJoints += context.getArgument("amount", Integer.class);
                                                            context.getSource().sendSuccess(() ->
                                                                    Component.translatable("gttcore.dyson_sphere_joint.set",
                                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonSphereJoints
                                                                    ), true);
                                                            return SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                        .then(literal("set").then(Commands.argument("amount", IntegerArgumentType.integer())
                                                        .executes(context -> {
                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonSphereJoints = context.getArgument("amount", Integer.class);
                                                            context.getSource().sendSuccess(() ->
                                                                    Component.translatable("gttcore.dyson_sphere_joint.set",
                                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonSphereJoints
                                                                    ), true);
                                                            return SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.translatable("gttcore.dyson_sphere_joint.query",
                                                            DysonWorldSavedData.getOrCreate(context.getSource().getLevel()).dysonSphereJoints
                                                    ), true);
                                            return SINGLE_SUCCESS;
                                        })
                                )
                        )
        );
    }
}
