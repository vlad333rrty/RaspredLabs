package ru.bmstu.iu9.lab5.data;

public class Request {
    private final RequestType type;
    private final String url;
    private final double result;

    public Request(RequestType type, String url, double result){
        this.type=type;
        this.url = url;
        this.result = result;
    }

    public RequestType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public double getResult() {
        return result;
    }
}
