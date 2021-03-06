package bms.player.beatoraja.skin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import bms.player.beatoraja.MainState;
import bms.player.beatoraja.play.bga.FFmpegProcessor;

public class SkinSourceMovie implements SkinSource {

	/**
	 * イメージ
	 */
	private FFmpegProcessor image;
	
	private boolean playing;

	private final int timer;
	
	private final TextureRegion region = new TextureRegion();

	public SkinSourceMovie(String s) {
		this(s, 0);
	}

	public SkinSourceMovie(String s, int timer) {
		image = new FFmpegProcessor(1);
		image.create(s);
		this.timer = timer;
	}

	public TextureRegion getImage(long time, MainState state) {
		if(!playing) {
			image.play(true);
			playing = true;
		}
		Texture tex = image.getFrame();
		if(tex != null) {
			region.setTexture(tex);
			region.setRegion(tex);
			return region;
		}
		return null;
	}

	public TextureRegion[] getImages(long time, MainState state) {
		return null;
	}
	
	public ShaderProgram getShader() {
		return image.getShader();
	}

//	private int getImageIndex(int length, long time, MainState state) {
//		if (timer != 0 && timer < 256) {
//			if (state.getTimer()[timer] == Long.MIN_VALUE) {
//				return 0;
//			}
//			time -= state.getTimer()[timer];
//		}
//		if (time < 0) {
//			return 0;
//		}
//		// System.out.println(index + " / " + image.length);
//		return (int) ((time * length / cycle) % length);
//	}

	public void dispose() {
		if (image != null) {
			image.dispose();
			image = null;
		}
	}
}
