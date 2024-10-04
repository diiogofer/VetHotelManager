package hva.core;

public abstract class HotelEntity {
    private String _id;
    private String _name;

    public HotelEntity(String id, String name) {
        _id = id;
        _name = name;
    }

    protected String name(){
        return _name;
    }

    protected String id(){
        return _id;
    }

    @Override
    public int hashCode(){
        return _id.hashCode();
    }


    @Override
    public abstract boolean equals(Object obj);
}
