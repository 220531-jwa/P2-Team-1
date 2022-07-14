package JavaTests;

import models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.ItemDAO;
import repositories.TicketDAO;
import services.ItemService;
import services.TicketService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {
    @InjectMocks
    private static ItemService mockIs;
    @Mock
    private static ItemDAO mockId;

    @BeforeEach
    public void setupEach(){
        mockIs = new ItemService(mockId);
    }

    @Test
    public void should_createNewItem(){
        //given
        Item mockItem = new Item("Initial Reality", 79.99, "Become a Warrior of Darkness and bring about the destruction of the world.", 1, 23, 1);
        //when
        when(mockId.createNewItem("Initial Reality", 79.99, "Become a Warrior of Darkness and bring about the destruction of the world.", 23, 23)).thenReturn(mockItem);
        //then
        assertEquals(mockItem, mockIs.createNewItem("Initial Reality", 79.99, "Become a Warrior of Darkness and bring about the destruction of the world.", 23, 23));
    }

    @Test
    public void should_removeInventory(){
        //given
        Item mockItem = new Item("Initial Reality", 79.99, "Become a Warrior of Darkness and bring about the destruction of the world.", 1, 23, 4);
        int inventory = mockItem.getInventory();
        //when
        when(mockId.checkoutRemoveInventory(1, 1)).thenReturn(inventory);
        //then
        assertEquals(inventory, mockIs.checkoutRemoveInventory(1, 1));
    }
}
