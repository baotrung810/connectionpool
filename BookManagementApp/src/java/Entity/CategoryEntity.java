
package Entity;

import java.util.List;

public class CategoryEntity {
    private int id;
    private String name;
    private String description;
   // set relationship with book entity
    List<BookEntity> booklist;
    public  CategoryEntity(){
        
 /*   public CategoryEntity(int id, String name, String description, List<BookEntity> booklist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.booklist = booklist;*/
    }

    public CategoryEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookEntity> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<BookEntity> booklist) {
        this.booklist = booklist;
    }
    
}
