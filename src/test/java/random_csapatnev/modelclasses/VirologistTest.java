package random_csapatnev.modelclasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class VirologistTest {
	private Virologist virologist;

	private Field field;
	private Field neighborField1;
	private Field neighborField2;

	//	for use function
	private Character character;
	private Agent agentMock;

	@Before
	public void setUp() {
		virologist = new Virologist("Test Virologist");
		field = new Field(0, 1);
		neighborField1 = new Field(0, 2);
		neighborField2 = new Field(1, 0);

		virologist.setCurrField(field);

		field.getNeighbours().add(neighborField1);
		field.getNeighbours().add(neighborField2);

		character = new Character("Test Character");

		agentMock = mock(Agent.class);

		character.setCurrField(field);
		character.craftedAgents.add(agentMock);
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
	public void move_ShouldFail_WhenParalyzed() {
		// Arrange
		virologist.isParalyzed = true;

		// Act
		virologist.move(neighborField1);

		// Assert
		assertEquals(field, virologist.getCurrField());
	}

	@Test
	public void move_ShouldSucceed_WhenMoveVitus() {
		// Arrange
		virologist.isVitus = true;

		// Act
		virologist.move(neighborField1);

		// Assert
		assertNotEquals(field, virologist.getCurrField());
	}
	
    @Test
    public void use_ShouldFail_WhenAgentIsNotCraftedByCharacter() {
        // Arrange
        Character otherCharacter = new Character("c");
        otherCharacter.setCurrField(character.currField);
        Agent otherAgent = mock(Agent.class);

        // Act
        otherCharacter.use(character, otherAgent);

        // Assert
        assertTrue(character.activeAgents.isEmpty());
        verifyNoMoreInteractions(otherAgent);
    }
    
	@Test
	public void use_ShouldFail_WhenCharacterIsNotOnCurrentField() {
		// Arrange
		Character otherCharacter = new Character("Character");
		otherCharacter.setCurrField(mock(Field.class));

		// Act
		character.use(otherCharacter, agentMock);

		// Assert
		verifyNoMoreInteractions(agentMock);
		assertTrue(otherCharacter.activeAgents.isEmpty());
	}

}
