package cn.nukkit.level.format.generic;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockWall;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.ChunkSection;
import cn.nukkit.level.format.anvil.Anvil;
import cn.nukkit.level.format.updater.ChunkUpdater;
import cn.nukkit.math.BlockFace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.powernukkit.tests.junit.jupiter.PowerNukkitExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(PowerNukkitExtension.class)
class BaseChunkTest {
    
    @Mock
    Anvil anvil;
    
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    BaseChunk chunk;
    
    @BeforeEach
    void setup() {
        chunk.setPaletteUpdatesDelayed(true);
        chunk.sections = new ChunkSection[16];
        chunk.sectionLength = 16;
        System.arraycopy(EmptyChunkSection.EMPTY, 0, chunk.sections, 0, 16);
        chunk.setProvider(anvil);
        chunk.providerClass = Anvil.class;
    }

    /**
     * Cloudburst Nukkit don't validate xyz, instead, it uses only the 4 least significant bits. We must do the same.
     */
    @Test
    void github1138() {
        chunk.setBlockAt(0x10, 0, 0, BlockID.BEDROCK);
        assertEquals(BlockID.BEDROCK, chunk.getBlockId(0, 0, 0));
        chunk.setBlockAt(0, 0, 0, BlockID.AIR);
        assertEquals(BlockID.AIR, chunk.getBlockId(0, 0, 0));

        chunk.setBlockAt(0, 0x10, 0, BlockID.BEDROCK);
        assertEquals(BlockID.BEDROCK, chunk.getBlockId(0, 0x10, 0));
        chunk.setBlockAt(0, 0x10, 0, BlockID.AIR);
        assertEquals(BlockID.AIR, chunk.getBlockId(0, 0x10, 0));

        chunk.setBlockAt(0, 0, 0x10, BlockID.BEDROCK);
        assertEquals(BlockID.BEDROCK, chunk.getBlockId(0, 0, 0));
        chunk.setBlockAt(0, 0, 0, BlockID.AIR);
        assertEquals(BlockID.AIR, chunk.getBlockId(0, 0, 0));

        chunk.setBlockAt(0x13, 0, 0, BlockID.BEDROCK);
        assertEquals(BlockID.BEDROCK, chunk.getBlockId(3, 0, 0));
        chunk.setBlockAt(3, 0, 0, BlockID.AIR);
        assertEquals(BlockID.AIR, chunk.getBlockId(3, 0, 0));

        chunk.setBlockAt(0, 0x13, 0, BlockID.BEDROCK);
        assertEquals(BlockID.BEDROCK, chunk.getBlockId(0, 0x13, 0));
        chunk.setBlockAt(0, 0x13, 0, BlockID.AIR);
        assertEquals(BlockID.AIR, chunk.getBlockId(0, 0x13, 0));

        chunk.setBlockAt(0, 0, 0x13, BlockID.BEDROCK);
        assertEquals(BlockID.BEDROCK, chunk.getBlockId(0, 0, 3));
        chunk.setBlockAt(0, 0, 3, BlockID.AIR);
        assertEquals(BlockID.AIR, chunk.getBlockId(0, 0, 3));
    }

    @Test
    void isBlockChangeAllowed() {
        BlockState allow = BlockState.of(BlockID.ALLOW);
        BlockState deny = BlockState.of(BlockID.DENY);
        BlockState border = BlockState.of(BlockID.BORDER_BLOCK);
        int x = 5;
        int baseY = 6;
        int z = 7;
        
        /////////////////////////
        chunk.setBlockState(x, baseY, z, deny);
        for (int y = 0; y < baseY; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY; y < 255; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        ////////////////////////////////
        chunk.setBlockState(x, baseY + 30, z, allow);
        for (int y = 0; y < baseY; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY; y < baseY + 30; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY + 30; y <= 255; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        ////////////////////////////////
        int chunkStart = 96;
        chunk.setBlockState(x, chunkStart, z, deny);
        for (int y = 0; y < baseY; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY; y < baseY + 30; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY + 30; y < chunkStart; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = chunkStart; y <= 255; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        ////////////////////////////////
        chunk.setBlockState(x, chunkStart + 15, z, allow);
        for (int y = 0; y < baseY; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY; y < baseY + 30; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY + 30; y < chunkStart; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = chunkStart; y < chunkStart + 15; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = chunkStart + 15; y <= 255; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        ////////////////////////////////
        chunk.setBlockState(x, 200, z, border);
        for (int y = 0; y <= 255; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        ////////////////////////////////
        chunk.setBlockState(x, 200, z, deny);
        for (int y = 0; y < baseY; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY; y < baseY + 30; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = baseY + 30; y < chunkStart; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = chunkStart; y < chunkStart + 15; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = chunkStart + 15; y < 200; y++) {
            assertTrue(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }

        for (int y = 200; y <= 255; y++) {
            assertFalse(chunk.isBlockChangeAllowed(x, y, z), "x:"+x+" y:"+y+" z:"+z);
        }
    }
    
    @Test
    void backwardCompatibilityUpdate2InvalidWalls() {
        int wallType = BlockWall.WallType.DIORITE.ordinal();
        int x = 3, y = 4, z = 5, invalid = 0b11_0000 | wallType;
        chunk.setBlock(x, y, z, BlockID.COBBLE_WALL, invalid);
        chunk.setBlock(x, y+1, z, BlockID.COBBLE_WALL, wallType);
        
        chunk.setBlock(x+1, y, z, BlockID.COBBLE_WALL, wallType);
        chunk.setBlock(x+1, y+1, z, BlockID.COBBLE_WALL, wallType);

        ChunkSection section = chunk.getSection(y >> 4);
        section.setContentVersion(1);
        
        assertEquals(BlockID.COBBLE_WALL, chunk.getBlockId(x, y, z));
        assertEquals(invalid, chunk.getBlockData(x, y, z));
        assertEquals(1, section.getContentVersion());
        
        Level level = mock(Level.class, Answers.CALLS_REAL_METHODS);
        doReturn("FakeLevel").when(level).getName();
        doReturn(chunk).when(level).getChunk(x >> 4, z >> 4);
        
        chunk.backwardCompatibilityUpdate(level);
        
        assertEquals(ChunkUpdater.getCurrentContentVersion(), section.getContentVersion());
        assertEquals(BlockID.COBBLE_WALL, chunk.getBlockId(x, y, z));
        assertEquals(BlockID.COBBLE_WALL, chunk.getBlockId(x, y+1, z));
        
        assertEquals(BlockID.COBBLE_WALL, chunk.getBlockId(x+1, y, z));
        assertEquals(BlockID.COBBLE_WALL, chunk.getBlockId(x+1, y+1, z));

        BlockWall wall = new BlockWall(wallType);
        wall.setWallPost(true);
        wall.setConnection(BlockFace.EAST, BlockWall.WallConnectionType.TALL);
        assertEquals(wall.getDamage(), chunk.getBlockData(x, y, z));
        
        wall.setWallPost(true);
        wall.clearConnections();
        wall.setConnection(BlockFace.EAST, BlockWall.WallConnectionType.SHORT);
        assertEquals(wall.getDamage(), chunk.getBlockData(x, y+1, z));
        
        wall.setWallPost(true);
        wall.clearConnections();
        wall.setConnection(BlockFace.WEST, BlockWall.WallConnectionType.TALL);
        assertEquals(wall.getDamage(), chunk.getBlockData(x+1, y, z));
        
        wall.setWallPost(true);
        wall.clearConnections();
        wall.setConnection(BlockFace.WEST, BlockWall.WallConnectionType.SHORT);
        assertEquals(wall.getDamage(), chunk.getBlockData(x+1, y+1, z));
    }
}
