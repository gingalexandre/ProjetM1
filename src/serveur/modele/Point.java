package serveur.modele;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;

public class Point implements Serializable {

	private static final long serialVersionUID = 1L;
	
	double x;
	double y;
	
	public Point(double x, double y) {
		super();
		this.x = ((double)((int) x));
		this.y = ((double)((int) y));
	}
	
	@JsonGetter("x")
    public double getX() {
        return x;
    }

	@JsonGetter("y")
    public double getY() {
        return y;
    }
   
    @Override
	public int hashCode() {
		return ((int) x)*3+((int) y)*7;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
    
    public String toString(){
    	return "x:"+this.x+"-y:"+this.y+"\n";
    }
    
    public int compareTo(Point p){
    	if(this.y>p.y){
    		return -10;
    	}
    	else if((this.y == p.y)&&(this.x > p.x)){
    		return -10;
    	}
    	else if((this.y == p.y)&&(this.x<p.x)){
    		return 10;
    	}
    	else{
    		return 0;
    	}
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
    
	
    
        
}
