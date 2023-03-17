package Object;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private List<String> villes = new ArrayList<>();
    private List<String> nomPropres = new ArrayList<>();
    private List<String> nomCommuns = new ArrayList<>();
    private List<String> verbes = new ArrayList<>();

    public String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void addToVilles(String text){
        this.villes.add(text);
    }
    public void addToVerbes(String text){
        this.verbes.add(text);
    }
    public void addToNomCommuns(String text){
        this.nomCommuns.add(text);
    }
    public void addToNomPropres(String text){
        this.nomPropres.add(text);
    }

    public List<String> getVilles() {
        return villes;
    }

    public List<String> getNomPropres() {
        return nomPropres;
    }

    public List<String> getNomCommuns() {
        return nomCommuns;
    }

    public List<String> getVerbes() {
        return verbes;
    }
}
