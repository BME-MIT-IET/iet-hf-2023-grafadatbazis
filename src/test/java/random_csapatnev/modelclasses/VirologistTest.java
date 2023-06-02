package random_csapatnev.modelclasses;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.Test;

public class VirologistTest {
	private Virologist virologist;
	private Field field;
	private Field neighborField1;
	private Field neighborField2;

	@Before
	public void setUp() {
		virologist = new Virologist("Test Virologist");
		field = new Field(0, 1);
		neighborField1 = new Field(0, 2);
		neighborField2 = new Field(1, 0);

		virologist.setCurrField(field);

		field.getNeighbours().add(neighborField1);
		field.getNeighbours().add(neighborField2);
	}

	@Test
	public void move_MoveToNeighbourField() {
		// Act
		virologist.move(neighborField1);

		// Assert
		assertEquals(neighborField1, virologist.getCurrField());
	}

	@Test
	public void move_ShouldFail_WhenMovingToNotNeighborField() {
		// Act
		virologist.move(new Field(5,0));

		// Assert
		assertEquals(field, virologist.getCurrField());
	}

	@Test
	public void move_shouldFail_WhenParalyzed() {
		// Arrange
		virologist.isParalyzed = true;

		// Act
		virologist.move(neighborField1);

		// Assert
		assertEquals(field, virologist.getCurrField());
	}

	/*@Test
	public void testMoveWhenVitus() {
		// Arrange
		virologist.isVitus = true;

		// Act
		virologist.move(field);

		// Assert
		//assertTrue(possibleFields.contains(virologist.getCurrField()));
		assertFalse(virologist.getCurrField().equals(field));
	}*/

}
