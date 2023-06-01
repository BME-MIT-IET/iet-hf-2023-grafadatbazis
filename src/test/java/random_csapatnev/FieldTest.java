package random_csapatnev;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class FieldTest {
	
	private Field field;
	
	@Mock
    private Bear bearMock;
    
	@Mock
    private Character character1Mock;

	@Mock
	private Character character2Mock;
	
	@Before
    public void setup() {
        field = new Field(0, 0);
        bearMock = mock(Bear.class);
        character1Mock = mock(Character.class);
        character2Mock = mock(Character.class);

        bearMock.name = "bear";
        character1Mock.name = "character";
        character2Mock.name = "virologist";
    }

	@Test
	public void isNeighbour_ReturnsTrue_WhenFieldIsNeighbour() {
	    // Arrange
	    Field field2 = new Field(1, 0);
	    
	    field.getNeighbours().add(field2);

	    // Act
	    boolean isNeighbour = field.isNeighbour(field2);

	    // Assert
	    assertTrue(isNeighbour);
	}
	
	@Test
	public void isNeighbour_ReturnsFalse_WhenFieldIsNotNeighbour() {
	    // Arrange
	    Field field2 = new Field(2, 0);

	    // Act
	    boolean isNeighbour = field.isNeighbour(field2);

	    // Assert
	    assertFalse(isNeighbour);
	}
	
	@Test
	public void isNeighbour_ReturnsFalse_WhenFieldIsNull() {
		 // Act
        boolean isNeighbour = field.isNeighbour(null);

        // Assert
        assertFalse(isNeighbour);
	}
	
	@Test
	public void isNeighbour_ReturnsFalse_WhenFieldIsSameAsNeighbour() {
		// Act
        boolean isNeighbour = field.isNeighbour(field);

        // Assert
        assertFalse(isNeighbour);
    }
	
    @Test
    public void bearInteract_CallsCharacterInteract_WhenCharacterNameDoesNotStartWithB() {
    	//Arrange
    	field.characters.add(character1Mock);
    	field.characters.add(bearMock);

        //Act
        field.bearInteract(bearMock);
        
        //Assert
        verify(bearMock, times(1)).characterInteract(character1Mock);
    }
    
    @Test
    public void bearInteract_CallsCharacterInteractOnMultipleCharacters_WhenCharactersNameDoesNotStartWithB() {
    	//Arrange
    	field.characters.add(character1Mock);
        field.characters.add(character2Mock);
    	field.characters.add(bearMock);
        
        //Act
        field.bearInteract(bearMock);
        
        //Assert
        verify(bearMock, times(1)).characterInteract(character1Mock);
        verify(bearMock, times(1)).characterInteract(character2Mock);
    }
    
    @Test
    public void bearInteract_DoesNotCallCharacterInteract_WhenCharacterNameStartsWithB() {
    	//Arrange
    	Bear bear2 = new Bear("bear");
    	field.characters.add(bear2);
    	field.characters.add(bearMock);
    	
    	//Act
        field.bearInteract(bearMock);
        
        //Assert
        verify(bearMock, never()).characterInteract(bear2);
    }
    
    @Test
    public void bearInteract_DoesNotCallCharacterInteractOnOtherCharacters_WhenCharactersNameStartsWithB() {
    	//Arrange
    	Bear bear2 = new Bear("bear");
    	Bear bear3 = new Bear("bear");
    	Bear bear4 = new Bear("bear");

    	field.characters.add(bear2);
    	field.characters.add(bear3);
    	field.characters.add(bear4);
    	field.characters.add(bearMock);
    	
    	//Act
        field.bearInteract(bearMock);

        //Assert
        verify(bearMock, times(0)).characterInteract(any(Bear.class));
    }
	
	@Test
	public void bearInteract__DoesNotCallCharacterInteract_WhenCharactersListIsEmpty() {
		// Act
		field.bearInteract(bearMock);	// Nincs karakter a mezőn


	    // Assert
	    verify(bearMock, never()).characterInteract(any(Character.class));
	}

}
