package 식별Idclass;

import java.io.Serializable;

public class Child2Id implements Serializable {
    private String parent;

    private String childId;

    public Child2Id() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
