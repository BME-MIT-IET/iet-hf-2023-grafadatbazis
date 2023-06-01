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
    private Character character1;
	private Character character2;
	
	@Mock
    private Bear bearMock;
	
	@Before
    public void setup() {
        field = new Field(0, 0);
        
        character1 = new Character("character");
        character2 = new Character("character");

        bearMock = mock(Bear.class);
        bearMock.name = "bear";
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
    	field.characters.add(character1);
    	field.characters.add(bearMock);

        //Act
        field.bearInteract(bearMock);
        
        //Assert
        verify(bearMock, times(1)).characterInteract(character1);
    }
    
    @Test
    public void bearInteract_CallsCharacterInteractOnMultipleCharacters_WhenCharactersNameDoesNotStartWithB() {
    	//Arrange
    	field.characters.add(character1);
        field.characters.add(character2);
    	field.characters.add(bearMock);
        
        //Act
        field.bearInteract(bearMock);
        
        //Assert
        verify(bearMock, times(1)).characterInteract(character1);
        verify(bearMock, times(1)).characterInteract(character2);
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
	
	@Test
	public void moveFrom_AddsCharacterToCurrentField() {
		// Arrange
	    Field field2 = new Field(0,1);
	    field2.characters.add(character1);
	
	    // Act
	    field.moveFrom(field2, character1);
	
	    // Assert
	    assertTrue(field.characters.contains(character1));
     }
	 
	 @Test
	 public void moveFrom_AddsMultipleCharactersToCurrentField() {
        // Arrange
        Field field2 = new Field(0,1);
        field2.characters.add(character1);
        field2.characters.add(character2);

        // Act
        field.moveFrom(field2, character1);
        field.moveFrom(field2, character2);


        // Assert
        assertTrue(field.characters.contains(character1));
        assertTrue(field.characters.contains(character2));
     }
	 
	 @Test
	 public void moveFrom_RemovesCharacterFromOtherField() {
        // Arrange
        Field field2 = new Field(0,1);
        field2.characters.add(character1);

        // Act
        field.moveFrom(field2, character1);

        // Assert
        assertFalse(field2.getCharacters().contains(character1));
     }
	 
	 @Test
	 public void moveFrom_RemovesMultipleCharacterFromOtherField() {
        // Arrange
        Field field2 = new Field(0,1);
        field2.characters.add(character1);
        field2.characters.add(character2);

        // Act
        field.moveFrom(field2, character1);
        field.moveFrom(field2, character2);

        // Assert
        assertFalse(field2.getCharacters().contains(character1));
        assertFalse(field2.getCharacters().contains(character2));
     }
	 
	 @Test
	 public void moveFrom_DoesNotRemoveCharacterFromOtherFieldIfNotPresent() {
        // Arrange
	    Field field2 = new Field(0,1);
        field.characters.add(character1);

        // Act
        field.moveFrom(field2, character1);

        // Assert
        assertTrue(field2.characters.contains(character1));
	  }
	 
	 @Test
	 public void moveFrom_DoesNotAddCharacterToOtherFieldIfNotPresent() {
        // Arrange
	    Field field2 = new Field(0,1);
        field.characters.add(character1);

        // Act
        field.moveFrom(field2, character1);

        // Assert
        assertFalse(field.characters.contains(character1));
	  }
	    
	 
	 
	 
	 
	   
	

}
