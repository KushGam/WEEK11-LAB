package week11.domain;

/** A class representing a possible destination country for delivering a parcel. (Provided Class)
 * @author Shane Moore
 */
public class Country {
	private String name;
	private double costToShipToCountry;
	
	/** Create a Country object to encapsulate the name, and the base cost to ship to that country. */
	public Country(String name, double costToShipToCountry)
	{
		this.name = name;
		this.costToShipToCountry = costToShipToCountry;
	}
	
	/** Returns the name of this country. */
	public String getName()
	{
		return name;
	}
	
	/** Returns the price to ship any parcels to this country. */
	public double getCostToShipToCountry()
	{
		return costToShipToCountry;
	}

	/** Returns true if another Country or String is provided that matches the name of this Country - otherwise returns false. */ 
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof String) {
			return this.name.equals(other);
		} else if (other instanceof Country) {
			Country otherC = (Country) other;
			return ( this.name.equals(otherC.name) );
		} else return false;
	}
}
