package com.github.stilllogic20.voidpower.addon.mekanism.common.patcher;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Objects;
import java.util.Optional;

import static org.objectweb.asm.Opcodes.*;

public class VariantPatcherTransformer implements IClassTransformer {

    private static final Logger LOGGER = LogManager.getLogger(VariantPatcherTransformer.class);

    private static final String[] TARGETS = {
        "mekanism.common.item.ItemTierInstaller",
        "mekanism.common.item.ItemControlCircuit"
    };

    private static boolean isTarget(String name) {
        for (String target : TARGETS) {
            if (target.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static Optional<MethodNode> find(ClassNode cn, String name, String desc) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(desc);
        for (MethodNode mn : cn.methods) {
            if (Objects.equals(mn.name, name) && Objects.equals(mn.desc, desc))
                return Optional.of(mn);
        }
        return Optional.empty();
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!isTarget(name)) {
            return basicClass;
        }

        LOGGER.info("Transforming class " + name);
        try {
            ClassReader cr = new ClassReader(basicClass);
            ClassNode cn = new ClassNode();
            cr.accept(cn, 0);

            find(cn, "getVariants", "()I").ifPresent(omn -> {
                cn.methods.remove(omn);
                MethodNode mn = new MethodNode(omn.access, omn.name, omn.desc, omn.signature, omn.exceptions.toArray(new String[0]));
                mn.access = omn.access;
                mn.visitCode();
                mn.visitMethodInsn(INVOKESTATIC, "mekanism/common/tier/BaseTier", "values",
                    "()[Lmekanism/common/tier/BaseTier;", false);
                mn.visitInsn(ARRAYLENGTH);
                mn.visitInsn(IRETURN);
                mn.visitEnd();
                cn.methods.add(mn);
            });

            ClassWriter cw = new ClassWriter(0);
            cn.accept(cw);
            return cw.toByteArray();
        } catch (Exception exception) {
            LOGGER.fatal("Unable to patch class " + name, exception);
            return new byte[0];
        }
    }

}
