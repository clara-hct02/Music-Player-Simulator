package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {
    private Song s1;

    @BeforeEach
    public void runBefore() {
        s1 = new Song("Hello World");
    }

    @Test
    public void testConstructor(){
        assertEquals("Hello World", s1.getName());
        assertFalse(s1.getLike());
    }

    @Test
    public void testLike(){
        s1.like();
        assertTrue(s1.getLike());
        s1.like();
        assertFalse(s1.getLike());
    }

}
