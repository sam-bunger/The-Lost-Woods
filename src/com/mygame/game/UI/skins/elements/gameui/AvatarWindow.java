package com.mygame.game.UI.skins.elements.gameui;

import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * @author lyze237
 */
public class AvatarWindow extends Window {
    public AvatarWindow (Skin skin) {
        super("<Name>", skin);

        defaults().pad(2);

        add(new TextButton("Avatar", skin)).width(64).height(64);

        Table status = new Table(skin);

        status.add(new Label("Health", skin));
        ProgressBar health = new ProgressBar(0, 100, 1, false, skin);
        health.setValue(75);
        status.add(health).width(100);

        status.row();

        status.add(new Label("Mana", skin));
        ProgressBar mana = new ProgressBar(0, 100, 1, false, skin);
        mana.setValue(24);
        status.add(mana).width(100);

        add(status);

        pack();
        setKeepWithinStage(true);
    }
}
