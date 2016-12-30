package io.github.phantamanta44.spaceres.proxy;

import io.github.phantamanta44.spaceres.render.RenderMap;
import io.github.phantamanta44.spaceres.render.tile.ResonanceChannelRenderer;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;

public class ClientProxy extends CommonProxy {

    @Override
    public void onPreInit() {
        super.onPreInit();
    }

    @Override
    public void onInit() {
        super.onInit();
        RenderMap.registerTile(TileResonanceChannel.class, new ResonanceChannelRenderer());
    }

    @Override
    public void onPostInit() {
        super.onPostInit();
    }

}
