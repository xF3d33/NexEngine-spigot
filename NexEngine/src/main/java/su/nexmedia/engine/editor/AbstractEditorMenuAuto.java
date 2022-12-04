package su.nexmedia.engine.editor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.NexPlugin;
import su.nexmedia.engine.api.editor.EditorButtonType;
import su.nexmedia.engine.api.menu.AbstractMenuAuto;
import su.nexmedia.engine.api.menu.IMenuClick;
import su.nexmedia.engine.api.menu.IMenuItem;
import su.nexmedia.engine.api.menu.MenuItem;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEditorMenuAuto<P extends NexPlugin<P>, T, L> extends AbstractMenuAuto<P, L> {

    protected final T parent;

    public AbstractEditorMenuAuto(@NotNull P plugin, @NotNull T parent, @NotNull String title, int size) {
        super(plugin, title, size);
        this.parent = parent;
    }

    public void loadItems(@NotNull IMenuClick click) {
        Map<EditorButtonType, Integer> types = new HashMap<>();
        this.setTypes(types);

        types.forEach((editorType, slot) -> {
            ItemStack item = editorType.getItem();
            IMenuItem menuItem = new MenuItem(item, (Enum<?>) editorType, slot);
            menuItem.setClick(click);
            this.addItem(menuItem);
        });
    }

    public abstract void setTypes(@NotNull Map<EditorButtonType, Integer> types);

    @Override
    public void onReady(@NotNull Player player, @NotNull Inventory inventory) {

    }
}