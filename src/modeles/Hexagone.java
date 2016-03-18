package modeles;

import commun.DistributeurType;

public class Hexagone {
	private int indexHexagone;
	private Ressource ressource;
	private int numero;
	private Ville[] villeAdj = new Ville[6];
	
	public final static int FORET = 1;
	public final static int CHAMPS = 2;
	public final static int CARRIERE = 3;
	public final static int MONTAGNE = 4;
	public final static int PRAIRIE = 5;
	
	public final static int MER = 6;
	public final static int DESERT = 7;

	private Point a;
	private Point b;
	private Point c;
	private Point d;
	private Point e;
	private Point f;
	
	private int type;

	public Hexagone(int indexHexagone, Point a, Point b, Point c, Point d, Point e, Point f, int type) {
		super();
		this.indexHexagone = indexHexagone;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.type = type;
	}
	
	
	
	
	public Point getA() {
		return a;
	}




	public Point getD() {
		return d;
	}




	public Hexagone(double xCentre, double yCentre, double size, int indexHexagone){
	 double x1 = xCentre;
     double y1 = yCentre + size;
     a = new Point(x1,y1);
     
     double x2 = xCentre + (Math.sqrt(3) / 2) * size;
     double y2 = yCentre + size / 2;
     b = new Point(x2,y2);
     
     double x3 = x2;
     double y3 = yCentre - size / 2;
     c = new Point(x3,y3);
     
     double x4 = x1;
     double y4 = yCentre - size;
     d = new Point(x4,y4);
     
     double x5 = xCentre - (Math.sqrt(3) / 2) * size;
     double y5 = y3;
     e = new Point(x5,y5);
     
     double x6 = x5;
     double y6 = y2;
     f = new Point(x6,y6);
     
     this.indexHexagone = indexHexagone;
     
     switch(indexHexagone){
     	case 0:
     	case 1:
     	case 2:
     	case 3:	
     	case 4:
     	case 8:
     	case 9:
     	case 14:
     	case 15:
     	case 21:
     	case 22:
     	case 27:
     	case 28:
     	case 32:
     	case 33:
     	case 34:
     	case 35:
     	case 36:
     		this.type = MER;
     		break;
     	case 18:
     		this.type = DESERT;
     		break;
     	default:
     		this.type = DistributeurType.getInstance().donnerType();
     	
     }
     
 }
	
	public Double[] getPoints(){
		Double[] res = {a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(), d.getY(), e.getX(), e.getY(), f.getX(), f.getY()};
		return res;
	}
	public int getIndexHexagone() {
		return indexHexagone;
	}
	public int getType() {
		return type;
	}
	
	
	
	
}
