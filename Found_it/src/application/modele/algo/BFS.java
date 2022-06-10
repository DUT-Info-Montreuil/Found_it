package application.modele.algo;

import java.util.ArrayList;

import application.modele.MainCharacter;
import application.modele.TileMap;

public class BFS {

	private ArrayList<Coord> file;
	private TileMap mapTile;
	private MainCharacter target;
	private Coord start;
	private int[][] bfs;
	private int range;
	private final int jumpMax;
	private final static int VIDE = -5, IMPOSSIBLE = -2, BLOCPHYSIQUE = -9;
	
	public BFS(MainCharacter target, TileMap map, int jumpMax, int range) {
		file = new ArrayList<Coord>();
		mapTile = map;
		this.jumpMax = jumpMax;
		this.target = target;
		this.range = range;
		bfs = new int[mapTile.getHeight()][mapTile.getWidth()];
	}
	public void launch() {
		initBFS();
		file.add(start);
		bfs[start.getY()][start.getX()] = 0;
		while (!file.isEmpty()) {
			for (Coord c : getCoordProximity(file.get(0))) {
				if (availableCoord(c) ) {
					bfs[c.getY()][c.getX()] = bfs[file.get(0).getY()][file.get(0).getX()] + 1;
					if (bfs[c.getY()][c.getX()] < range)
						file.add(c);
				}
			}
			file.remove(0);
		}
	}
	public int[][] getBFS() {
		return bfs;
	}
	private void initBFS() {
		start = new Coord(mapTile.getXCharacterInMap(target.getX()),mapTile.getYIndiceHeight(target.getYBOT()));
		for (int i = 0 ; i < mapTile.getHeight() ; i++)
			for (int j = 0 ; j < mapTile.getWidth() ; j++)
				if (mapTile.wasTransparent(mapTile.getCodeTuileWithIndice(j, i)))
					bfs[i][j] = VIDE;
				else 
					bfs[i][j] = BLOCPHYSIQUE;
		initJumpAllow();
		file.clear();
	} 

	private void initJumpAllow() {
		for (int i = 0 ; i < mapTile.getHeight() ; i++)
			for (int j = 0 ; j < mapTile.getWidth() ; j++)
				if (bfs[i][j] == BLOCPHYSIQUE)
					if (i > jumpMax && outOfRangeJump(i, j))
						bfs[i - jumpMax][j] = IMPOSSIBLE;
	}

	private boolean outOfRangeJump(int i, int j) {
		int count = 1;
		while (count <= jumpMax - 1  && bfs[i - count][j] != BLOCPHYSIQUE)
			count++;
		return count > jumpMax - 1 ;
	}

	
	private ArrayList<Coord> getCoordProximity(Coord c) {
		ArrayList<Coord> prox = new ArrayList<Coord>();
		if (c.getY() < mapTile.getHeight() - 1)
			prox.add(new Coord(c.getX(),c.getY() + 1));
		if (c.getY() > 0)
			prox.add(new Coord(c.getX(),c.getY() - 1));
		if (c.getX() < mapTile.getWidth() - 1)
			prox.add(new Coord(c.getX() + 1,c.getY()));
		if (c.getX() > 0)
			prox.add(new Coord(c.getX() - 1,c.getY()));
		return prox;
	}

	private boolean availableCoord(Coord c) {
		if (c.getY() < mapTile.getHeight() - 1 && bfs[c.getY()+1][c.getX()] != IMPOSSIBLE) {
			if (bfs[c.getY()][c.getX()] == IMPOSSIBLE)
				return itsPossibleTOP(c) || itsPossibleLeft(c) || itsPossibleRight(c);
			return bfs[c.getY()][c.getX()] == VIDE;
		}
		return false;
	}
	private boolean itsPossibleTOP(Coord c) {
		return c.getY() > 0 && bfs[c.getY() - 1][c.getX()] != VIDE && bfs[c.getY() - 1][c.getX()] != IMPOSSIBLE && bfs[c.getY() - 1][c.getX()] != BLOCPHYSIQUE;
	}
	private boolean itsPossibleLeft(Coord c) {
		if (c.getX() > 0 && c.getY() < mapTile.getHeight() - 1)
			return bfs[c.getY() + 1][c.getX() - 1] == BLOCPHYSIQUE;
		return false;
	}
	private boolean itsPossibleRight(Coord c) {
		if (c.getX() < mapTile.getWidth() - 1 && c.getY() < mapTile.getHeight() - 1)
			return bfs[c.getY() + 1][c.getX() + 1] == BLOCPHYSIQUE;
		return false;
	}
	public void afficherBFS() {
		for (int[] t : bfs) {
			for (int x : t) {
				for (int a = String.valueOf(x).length() ; a < 2 ; a++)
					System.out.print(" ");
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
	public boolean goRight(int x, int y) {
		if (x < mapTile.getWidth() - 1)	
			return bfs[y][x] - 1 == bfs[y][x + 1];
		return false;
	}
	public boolean goLeft(int x, int y) {
		if (x > 0)	
			return bfs[y][x] - 1 == bfs[y][x - 1];
		return false;
	}
	public boolean goUp(int x, int y) {
		if (y < mapTile.getHeight() - 1)	
			return bfs[y][x] - 1 == bfs[y - 1][x ];
		return false;
	}
	public boolean isNear(MainCharacter m, int value) {
		return bfs[mapTile.getYIndiceHeight(m.getY())][mapTile.getXCharacterInMap(m.getX())] <= value;
	}


	
}
