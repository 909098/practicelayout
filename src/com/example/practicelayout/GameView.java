package com.example.practicelayout; 

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Runnable{
    Paint paint = new Paint();
	int i = 0;
	int j = 0;
	int type, grasstype, canvasWidth, canvasHeight, vinetype, columntype, guyX, cellWidth, cellHeight, fps, guyY;
	double guyYTemp;
	Bitmap grassLeft, grassRight, grassMid1, grassMid2, vine1, vine2, vine3, vine4, cT, cM, cB, star, guy;
	long lastUpdate = 0;
    long sleepTime=0;
    Canvas canvas;
	public GameView(Context context) {
	// TODO Auto-generated constructor stub
		super(context);
		grassLeft = BitmapFactory.decodeResource(getResources(), R.drawable.grassleft);
		grassRight = BitmapFactory.decodeResource(getResources(), R.drawable.grassright);
		grassMid1 = BitmapFactory.decodeResource(getResources(), R.drawable.grassmid1);
		grassMid2 = BitmapFactory.decodeResource(getResources(), R.drawable.grassmid2);
		vine1 = BitmapFactory.decodeResource(getResources(), R.drawable.vine);
		vine2 = BitmapFactory.decodeResource(getResources(), R.drawable.vine);
		vine3 = BitmapFactory.decodeResource(getResources(), R.drawable.vine);
		vine4 = BitmapFactory.decodeResource(getResources(), R.drawable.vine);
		cT = BitmapFactory.decodeResource(getResources(), R.drawable.columntop);
		cM = BitmapFactory.decodeResource(getResources(), R.drawable.columnmid);
		cB = BitmapFactory.decodeResource(getResources(), R.drawable.columnbottom);
		star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
		guy = BitmapFactory.decodeResource(getResources(), R.drawable.guy);
}
	

	@Override
    public void onDraw(Canvas canvas) {
    	int xMap = 13;
    	guyX = 100;
    	guyY = 100;
    	int yMap = 9;
    	vinetype = 0;
    	columntype = 0;
    	grasstype = 0;
		canvasWidth = canvas.getWidth();
		canvasHeight = canvas.getHeight();
		paint.setColor(Color.rgb(135, 206, 250));
		canvas.drawRect(0, 0, canvasWidth, canvasHeight, paint);
		cellWidth = canvasWidth/17;
		cellHeight = canvasHeight/10;
		grassLeft = Bitmap.createScaledBitmap(grassLeft, cellWidth,
                cellHeight, true);
		grassRight = Bitmap.createScaledBitmap(grassRight, cellWidth,
                cellHeight, true);
		grassMid1 = Bitmap.createScaledBitmap(grassMid1, cellWidth,
                cellHeight, true);
		grassMid2 = Bitmap.createScaledBitmap(grassMid2, cellWidth,
                cellHeight, true);
		vine1 = Bitmap.createScaledBitmap(vine1, cellWidth,
                cellHeight, true);
		vine2 = Bitmap.createScaledBitmap(vine1, cellWidth,
                cellHeight, true);
		vine3 = Bitmap.createScaledBitmap(vine1, cellWidth,
                cellHeight, true);
		vine4 = Bitmap.createScaledBitmap(vine1, cellWidth,
                cellHeight, true);
		cT = Bitmap.createScaledBitmap(cT, cellWidth,
                cellHeight, true);
		cM = Bitmap.createScaledBitmap(cM, cellWidth,
                cellHeight, true);
		cB = Bitmap.createScaledBitmap(cB, cellWidth,
                cellHeight, true);
		star = Bitmap.createScaledBitmap(star, cellWidth,
                cellHeight, true);
		guy = Bitmap.createScaledBitmap(guy, cellWidth,
                (int) (cellHeight * 1.5), true);
    	int[][] gameMap = null;
    	if(levelSelect.level==1){
    	gameMap = new int[][]{
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 15},	
    			{0, 0, 0, 0, 0, 0, 0, 7, 8, 3, 8, 9, 15},
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 15},
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 6, 0, 15},
    			{0, 0, 7, 8, 3, 8, 8, 8, 8, 8, 9, 0, 15},
    			{0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 15},
    			{0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 15},
    			{1, 0, 0, 0, 2, 0, 6, 0, 0, 0, 0, 0, 15},
    			{7, 8, 8, 8, 8, 8, 9, 0, 0, 0, 0, 0, 15},
    	};
    	}else if(levelSelect.level==2){
    		gameMap = new int[][]{
        			{0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 6, 0, 15},	
        			{0, 7, 8, 8, 8, 3, 8, 8, 8, 3, 8, 9, 15},
        			{0, 0, 0, 0, 0, 2, 6, 0, 0, 2, 0, 0, 15},
        			{0, 0, 7, 8, 8, 8, 9, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 15},
        			{1, 6, 0, 0, 0, 0, 0, 0, 0, 2, 0, 6, 15},
        			{7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 15},
        	};
    	}else if(levelSelect.level==3){
		gameMap = new int[][]{
        			{0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6, 0, 15},	
        			{0, 7, 8, 8, 3, 9, 0, 0, 7, 3, 8, 9, 15},
        			{0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 2, 6, 0, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 7, 8, 8, 3, 8, 8, 9, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 15},
        			{0, 0, 1, 0, 0, 0, 2, 6, 0, 0, 0, 0, 15},
        			{0, 0, 7, 8, 8, 8, 8, 9, 0, 0, 0, 0, 15},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15}
        	};
		}else if(levelSelect.level==4){
		gameMap = new int[][]{
        			{0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15},	
        			{0, 7, 8, 8, 8, 8, 8, 8, 8, 3, 9, 0, 15},
        			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 6, 0, 0, 2, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 7, 8, 8, 9, 0, 0, 15},
        			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15},
        			{0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 15},
					{0, 0, 0, 7, 8, 9, 0, 0, 0, 0, 0, 0, 15}
        	};
		}else if(levelSelect.level==5){
			gameMap = new int[][]{
			{0, 0, 6, 0, 0, 0, 6, 0, 0, 6, 0, 0, 15}, 
			{0, 0, 7, 3, 8, 8, 8, 8, 3, 9, 0, 0, 15},
			{0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 15},
			{0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 15},
			{0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 15},
			{7, 3, 8, 8, 9, 0, 0, 7, 8, 8, 3, 9, 15},
			{0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 15},
			{1, 2, 0, 6, 0, 0, 0, 0, 6, 0, 2, 0, 15},
			{7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 15}
			};
		}
    	 while(yMap > j){
    		 while(xMap > i){
    			 type = gameMap[j][i];
    			 drawImage(canvas);
    			 i++;

    		 }
    		 j++;
    		 i = 0;
    	 }

    	}
    	public void drawImage(Canvas canvas){

    		paint.setStrokeWidth(0);
    		paint.setStyle(Paint.Style.FILL_AND_STROKE);
    		if(type==7){
    			canvas.drawBitmap(grassLeft, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    		}else if(type == 0){
    			paint.setColor(Color.rgb(135, 206, 250));
    			canvas.drawRect(i*cellWidth+24, j*cellHeight+(canvasHeight/20), i*cellWidth+24+cellWidth, j*cellHeight+cellHeight+(canvasHeight/20), paint);
    		}else if(type == 2){
    			if(vinetype==0){
        			canvas.drawBitmap(vine1, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
        			vinetype++;
        			}else if(vinetype==1){
        				vinetype++;
            			canvas.drawBitmap(vine2, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
        			}else if(vinetype==2){
        				vinetype++;
        				canvas.drawBitmap(vine3, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
        			}else if(vinetype==3){
        				canvas.drawBitmap(vine4, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
        				vinetype = 0;
        			}
    		}else if(type == 3){
        				if(grasstype==0){
        	    			canvas.drawBitmap(grassMid1, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
        	    			grasstype++;
        	    			}else{
        	    				grasstype--;
        	        			canvas.drawBitmap(grassMid2, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
        	    			}
            			if(vinetype==0){
                			canvas.drawBitmap(vine1, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
                			vinetype++;
                			}else if(vinetype==1){
                				vinetype++;
                    			canvas.drawBitmap(vine2, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
                			}else if(vinetype==2){
                				vinetype++;
                				canvas.drawBitmap(vine3, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
                			}else if(vinetype==3){
                				canvas.drawBitmap(vine4, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
                				vinetype = 0;
                			}
    					}else if(type == 8){
    						if(grasstype==0){
    							canvas.drawBitmap(grassMid1, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    							grasstype++;
    						}else{
    							grasstype--;
    							canvas.drawBitmap(grassMid2, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    						}
    					}else if(type == 9){
    						canvas.drawBitmap(grassRight, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    					}else if(type == 15){
    						if(columntype==0){
    							canvas.drawBitmap(cT, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    							columntype++;
    						}
    						if(columntype < 9){
    							canvas.drawBitmap(cM, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    							columntype++;
    						}else{
    							canvas.drawBitmap(cB, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    							columntype = 0;
    						}
    					}else if(type == 6){
    						canvas.drawBitmap(star, i*cellWidth+24, j*cellHeight+(canvasHeight/20), null);
    					}else if(type == 1){
    						guyX = i*cellWidth+24;
    						int temp = cellHeight/2;
    						guyY = j*cellHeight - temp +(canvasHeight/20);
    					}
    		paint.setColor(Color.BLACK);
    		paint.setStrokeWidth(cellHeight);
    		paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(0, 0, canvasWidth, canvasHeight, paint);
            canvas.drawBitmap(guy, guyX, guyY, null);
    }



		@Override
		public void run() {
			// TODO Auto-generated method stub
			long lastTimeChanged = System.currentTimeMillis();
			
			while (true) {
				if (Thread.interrupted())
					return;
				
				if (System.currentTimeMillis() >= (lastTimeChanged + 75)){
					
						
					lastTimeChanged = System.currentTimeMillis();
				}
				
				refreshEntities();
			}
		}


		private void refreshEntities() {
			// TODO Auto-generated method stub
			if(Game.mLastX > 2){
			}
		}
    }


