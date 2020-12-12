package ru.bmstu.iu9.lab5.data;

public class Request {
    private final RequestType type;
    private final String id;
    private final long result;

    public Request(RequestType type, String id, long result){
        this.type=type;
        this.id = id;
        this.result = result;
    }

    public RequestType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public long getResult() {
        return result;
    }
}
