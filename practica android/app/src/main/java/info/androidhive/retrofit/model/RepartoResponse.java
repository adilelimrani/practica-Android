
package info.androidhive.retrofit.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepartoResponse implements Serializable
{

    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = 2047906539841986264L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RepartoResponse() {
    }

    /**
     * 
     * @param id
     * @param cast
     * @param crew
     */
    public RepartoResponse(List<Cast> cast, List<Crew> crew, Integer id) {
        super();
        this.cast = cast;
        this.crew = crew;
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
