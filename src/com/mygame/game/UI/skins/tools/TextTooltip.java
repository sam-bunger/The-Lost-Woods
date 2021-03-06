package com.mygame.game.UI.skins.tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/** A tooltip that shows a label.
 * @author Nathan Sweet */
public class TextTooltip extends Tooltip<Label> {
	public TextTooltip (String text, Skin skin) {
		this(text, TooltipManager.getInstance(), skin.get(TextTooltipStyle.class));
	}

	public TextTooltip (String text, Skin skin, String styleName) {
		this(text, TooltipManager.getInstance(), skin.get(styleName, TextTooltipStyle.class));
	}

	public TextTooltip (String text, TextTooltipStyle style) {
		this(text, TooltipManager.getInstance(), style);
	}

	public TextTooltip (String text, TooltipManager manager, Skin skin) {
		this(text, manager, skin.get(TextTooltipStyle.class));
	}

	public TextTooltip (String text, TooltipManager manager, Skin skin, String styleName) {
		this(text, manager, skin.get(styleName, TextTooltipStyle.class));
	}

	public TextTooltip (String text, final TooltipManager manager, TextTooltipStyle style) {
		super(null, manager);

		Label label = new Label(text, style.label);
		label.setWrap(true);

		container.setActor(label);
		container.width(new Value() {
			public float get (Actor context) {
				return Math.min(manager.maxWidth, container.getActor().getGlyphLayout().width);
			}
		});

		setStyle(style);
	}

	public void setStyle (TextTooltipStyle style) {
		if (style == null) throw new NullPointerException("style cannot be null");
		if (!(style instanceof TextTooltipStyle)) throw new IllegalArgumentException("style must be a TextTooltipStyle.");
		container.getActor().setStyle(style.label);
		container.setBackground(style.background);
		container.pack();
		container.pack(); // Second pack is needed to recompute the label pref height in case the label wrapped on the first pack.
	}

	/** The style for a text tooltip, see {@link TextTooltip}.
	 * @author Nathan Sweet */
	static public class TextTooltipStyle {
		public LabelStyle label;
		/** Optional. */
		public Drawable background;

		public TextTooltipStyle () {
		}

		public TextTooltipStyle (LabelStyle label, Drawable background) {
			this.label = label;
			this.background = background;
		}

		public TextTooltipStyle (TextTooltipStyle style) {
			this.label = new LabelStyle(style.label);
			background = style.background;
		}
	}
}