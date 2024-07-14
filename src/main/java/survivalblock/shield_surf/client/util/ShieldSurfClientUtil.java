package survivalblock.shield_surf.client.util;

import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class ShieldSurfClientUtil {

    public static RenderLayer getOrbitingShieldsRenderLayer(boolean showBody, boolean translucent, boolean showOutline, Identifier texture, Model model) {
        if (showOutline) {
            return RenderLayer.getOutline(texture);
        }
        if (translucent) {
            return null;
        }
        if (showBody) {
            return model.getLayer(texture);
        }
        return null;
    }
}
