package random_csapatnev;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {

	@Test
	public void isNeighbour() {
	    // Arrange
	    Field field1 = new Field(0, 0);
	    Field field2 = new Field(1, 0);

	    field1.getNeighbours().add(field2);

	    // Act
	    boolean isNeighbour = field1.isNeighbour(field2);

	    // Assert
	    assertTrue(isNeighbour);
	}
	
	@Test
	public void isNotNeighbour() {
	    // Arrange
	    Field field1 = new Field(0, 0);
	    Field field2 = new Field(2, 0);

	    // Act
	    boolean isNeighbour = field1.isNeighbour(field2);

	    // Assert
	    assertFalse(isNeighbour);
	}
	
	@Test
	public void isNotExistingFieldNeighbour() {
	    // Arrange
	    Field field1 = new Field(0, 0);
	    Field nonExistingField = null;

	    // Act
	    boolean isNeighbour = field1.isNeighbour(nonExistingField);

	    // Assert
	    assertFalse(isNeighbour);
	}
	
	@Test
	public void isSelfNeighbour() {
	    // Arrange
	    Field field = new Field(0, 0);

	    // Act
	    boolean isNeighbour = field.isNeighbour(field);

	    // Assert
	    assertFalse(isNeighbour);
	}

}
