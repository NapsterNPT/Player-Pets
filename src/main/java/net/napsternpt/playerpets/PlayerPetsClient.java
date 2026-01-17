package net.napsternpt.playerpets;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import java.util.Map;

public class PlayerPetsClient implements ClientModInitializer {
    private void loadModels(ResourceManager manager) {
        Map<Identifier, Resource> resources =
                manager.findResources(
                        "models",
                        id -> id.getNamespace().equals("playerpets")
                                && id.getPath().endsWith(".json")
                );

        for (Identifier id : resources.keySet()) {
            System.out.println("[Player Pets] Model found: " + id);
        }
    }

    private void loadTextures(ResourceManager manager) {
        Map<Identifier, Resource> resources =
                manager.findResources(
                        "textures",
                        id -> id.getNamespace().equals("playerpets")
                                && id.getPath().endsWith(".png")
                );

        for (Identifier id : resources.keySet()) {
            System.out.println("[Player Pets] Texture found: " + id);
        }
    }

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
                .registerReloadListener(new SimpleSynchronousResourceReloadListener() {

                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of("playerpets", "model_loader");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        loadModels(manager);
                        loadTextures(manager);
                    }
                });

    }
}
