package hva.core;

public abstract class HotelEntity {
    private String _id;
    private String _name;

    public HotelEntity(String id, String name) {
        this._id = id;
        this._name = name;
    }

    protected String name(){
        return _name;
    }

    protected String id(){
        return _id;
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}
