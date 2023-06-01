package random_csapatnev;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class FieldTest {
	
	private Field field;
	
	@Before
    public void setup() {
        field = new Field(0, 0);
    }

	@Test
	public void isNeighbour() {
	    // Arrange
	    Field field2 = new Field(1, 0);
	    
	    field.getNeighbours().add(field2);

	    // Act
	    boolean isNeighbour = field.isNeighbour(field2);

	    // Assert
	    assertTrue(isNeighbour);
	}
	
	@Test
	public void isNotNeighbour() {
	    // Arrange
	    Field field2 = new Field(2, 0);

	    // Act
	    boolean isNeighbour = field.isNeighbour(field2);

	    // Assert
	    assertFalse(isNeighbour);
	}
	
	@Test
	public void isNotExistingFieldNeighbour() {
		 // Act
        boolean isNeighbour = field.isNeighbour(null);

        // Assert
        assertFalse(isNeighbour);
	}
	
	@Test
	public void isSelfNeighbour() {
		 // Act
        boolean isNeighbour = field.isNeighbour(field);

        // Assert
        assertFalse(isNeighbour);
	}

}
