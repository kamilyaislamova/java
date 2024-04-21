package markup;

import java.util.List;

public class OrderedList extends ListBB{

    public OrderedList(List<ListItem> l) {
        super(l);
        symOpen = "[list=1]";
    }
    
}
