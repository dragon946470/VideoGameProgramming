package com.game.dungeongame.world;

import java.util.ArrayList;

import com.game.dungeongame.world.characters.Bat;
import com.game.dungeongame.world.characters.Boss;
import com.game.dungeongame.world.characters.Player;
import com.game.dungeongame.world.characters.Slime;
import com.game.dungeongame.world.map.Tile;
import com.game.dungeongame.world.map.TileMap;
import com.game.dungeongame.world.objects.DecoObject;
import com.game.dungeongame.world.objects.Door;
import com.game.dungeongame.world.objects.PickupObject;
import com.game.utils.TextLoader;

public class WorldLoader {

	public static World load(String worldPath) {

		String map[] = TextLoader.loadTextFileAsString(worldPath).split(" ");

		// load tiles
		int rows = Integer.parseInt(map[0]);
		int cols = Integer.parseInt(map[1]);

		Tile tiles[][] = new Tile[rows][cols];
		String[] tile = null;
		int count = 2;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				tile = map[count++].split("-");
				int type = Integer.parseInt(tile[1]);

				switch (type) {

				case Tile.EMPTY:
					tiles[i][j] = new Tile(null, Tile.EMPTY);
					break;

				case Tile.SOLID:
					tiles[i][j] = new Tile(tile[0], Tile.SOLID);
					break;
				case Tile.NON_SOLID:
					tiles[i][j] = new Tile(tile[0], Tile.NON_SOLID);
					break;
				}
			}
		}

		TileMap tileMap = new TileMap(rows, cols, tiles);

		// loading objects
		ArrayList<DecoObject> decoObjects = new ArrayList<DecoObject>();
		ArrayList<PickupObject> pickupObjects = new ArrayList<PickupObject>();
		ArrayList<Slime> slimes = new ArrayList<Slime>();
		ArrayList<Bat> bats = new ArrayList<Bat>();
		Player player = null;
		Boss boss = null;
		ArrayList<Door> doors = new ArrayList<Door>();
		String object = null;
		int x = 0, y = 0;
		for (int i = count; i < map.length; i += 3) {
			object = map[i];
			x = Integer.parseInt(map[i + 1]);
			y = Integer.parseInt(map[i + 2]);

			switch (object) {

			case "player":
				player = new Player(tileMap, x, y);
				break;
				
			case "boss":
				boss = new Boss(tileMap,x,y);
				break;

			case "coin":
				pickupObjects.add(new PickupObject(x, y, "coin", PickupObject.COIN,1));
				break;

			case "potion":
				pickupObjects.add(new PickupObject(x, y, "potion", PickupObject.POTION,2));
				break;
				
			case "gun":
				pickupObjects.add(new PickupObject(x, y, "gun", PickupObject.GUN,1));
				break;

			case "slime":
				slimes.add(new Slime(tileMap, x, y));
				break;

			case "bat":
				bats.add(new Bat(tileMap, x, y));
				break;
				
			case "door":
				int worldName = Integer.parseInt(map[i + 3]);
				int xx = Integer.parseInt(map[i + 4]) * TileMap.TILE_WIDTH;
				int yy = Integer.parseInt(map[i + 5]) * TileMap.TILE_HEIGHT;
				
				doors.add(new Door(x,y,worldName,xx,yy));
				i+=3;
				break;
			default:
				decoObjects.add(new DecoObject(x, y, object,2));
				break;

			}

		}

		World world = new World(tileMap, decoObjects, pickupObjects, slimes, bats);
		world.setPlayer(player);
		world.setBoss(boss);
		world.setDoors(doors);

		return world;
	}
}
