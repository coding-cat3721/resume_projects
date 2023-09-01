package edu.nyu.cs.recursion_exercise;

import processing.core.PApplet;
import processing.core.PConstants;
/**
 * The following applet allows you to explore the Mandelbrot set by zooming in by dragging the 
 * mouse. 
 * 
 * 
 * @Auther,??, with comments by Jessy Huang (devils-advocate3721)
 */

 
public final class Mandelbrot extends PApplet {
	private int max = 64;// max number of iteration
	private float[][] colors = new float[48][3];// array of colors for drawing
	private double viewX = 0.0;//represent x-axle of the selected zoom-in area
	private double viewY = 0.0;//represent y-axle of the selected zoom-in area 
	private double zoom = 1.0;//zoom-in scale
	private int mousePressedX;//store the x-axle value of mouse pressed point.
	private int mousePressedY;//store the y-axle value of mouse pressed point.
	private boolean renderNew = true;//flag to indicate if need to redraw
	private boolean drawBox = false;//flag to show if need to draw the zoom-in selected area box
/**
 * applet panel size width 600x height 400
 */
	public void settings() {
		this.size(600,400);
	}
// method to initialize the color array
	public void setup() {
		for (int i = 0; i < colors.length; i++) {
			int c = 2 * i * 256 / colors.length;
			if (c > 255)
				c = 511 - c;
			float[] color = {c, c, c};
			this.colors[i] = color;
		}
	}

	public void draw() {

		if (!renderNew && !this.drawBox) return;
		this.background(0, 0, 0);
		//draw red recteccular without filling on the selected zoom-in area.
		if (this.drawBox) {
			this.noFill();
			this.stroke(255, 0, 0);
			rect(this.mousePressedX, this.mousePressedY, this.mouseX - this.mousePressedX, this.mouseY - this.mousePressedY);
		}
		//loop through each point in (width x height) window, converting from Java screen coordinate 
		//to math coordinate, then call function mandel to decide if point (x,y) is in Mandelbrot set
		//if point is in Mandelbrot set, draw black 
		//if not in Mandelbrot set, draw the colour which is mapped in color array based on the number 
		//of iteration completed.
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				double r = zoom / Math.min(this.width, this.height);
				double dx = 2.5 * (x * r + this.viewX) - 2.0;
				double dy = 1.25 - 2.5 * (y * r + this.viewY);
				int value = this.mandel(dx, dy);
				float[] color = this.colors[value % this.colors.length];
				this.stroke(color[0], color[1], color[2]);
				this.line(x, y, x, y);
			}
		}
		
		this.textAlign(PConstants.CENTER);
		this.text("Click and drag to draw an area to zoom into.", this.width / 2, this.height-20);

		this.renderNew = false;
	}
/**  
 * this method is to perform the Mandelbrot iteration for point (px, py), if point (px,py) is 
 * in the Mandelbrot set, return zero which is colour black in the colour array, otherwise return 
 * the number of iteration.
 * @param px, double, x axle value 
 * @param py, double, y axle value 
 * @return, int, return zero if (x,y) is mandel set, otherwise return the number of iteration.
 */
	private int mandel(double px, double py) {
		double zx = 0.0, zy = 0.0;
		double zx2 = 0.0, zy2 = 0.0;
		int value = 0;
		//while loop to check if point (px, py) belong to Mandelbrot set.
		//the point is considered as in the Mandelbrot set if still satisfy zx2+zy2 <4 after 
		//the "max" number of iteration is performed. 
			while (value < this.max && zx2 + zy2 < 4.0) {
			zy = 2.0 * zx * zy + py;//math equation to check Mandelbrot set
			zx = zx2 - zy2 + px;//math equation to check Mandelbrot set
			zx2 = zx * zx;
			zy2 = zy * zy;
			value++;
		}
		// if max iterations are reached, return 0, otherwise, return the number of iteration. 
		return value == this.max ? 0 : value;
	}
/**
 * This method is to store the current mouseX, mouseY into variable 
 * mousePressedX, mousePressedY whenever mouse is pressed.
 */
	public void mousePressed() {
		this.mousePressedX = this.mouseX;
		this.mousePressedY = this.mouseY;
		this.drawBox = true;
	}

	/**
	 * this method is to take actions when different mouse buttons are released.
	 */
	public void mouseReleased() {
		int mouseReleasedX = this.mouseX;
		int mouseReleasedY = this.mouseY;
		//if "left" mouse button is released, update viewX, viewY, zoom
		if (this.mouseButton == PConstants.LEFT) {
			if (mouseReleasedX != mousePressedX && mouseReleasedY != mousePressedY) {
				int w = this.width;
				int h = this.height;
				//calcuate viewX, viewY, zoom-in scale
				this.viewX += this.zoom * Math.min(mouseReleasedX, mousePressedX) / Math.min(w, h);
				this.viewY += this.zoom * Math.min(mouseReleasedY, mousePressedY) / Math.min(w, h);
				this.zoom *= Math.max((double)Math.abs(mouseReleasedX - mousePressedX) / w, (double)Math.abs(mouseReleasedY - mousePressedY) / h);
			}
		}
		//if "right" mouse button is released, update variable max
		else if (this.mouseButton == PConstants.RIGHT) {
			this.max += max / 4;
		}
		else {
			this.max = 64;
			this.viewX = this.viewY = 0.0;
			this.zoom = 1.0;
		}

		this.drawBox = false;
		this.renderNew = true;
	}

	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.recursion_exercise.Mandelbrot");
	}


}