package week11.domain;

/** Represents a particular parcel.  (Provided Class)
 * @author Shane Moore
 */
public class Parcel {
	private String destinationAddr;				// Where is the parcel being sent to?
	private Country countryDest;				// Which country the parcel is being sent to?
	private double weight;					// How heavy is the parcel?
	private boolean isFragile;				// Whether the parcel is fragile, needing special care
	private int sizeCategory;					// The type of parcel, see below for possibilities
	
	public static final int FLAT = 1;		// Items that are flat, like thin books, posters.
	public static final int SMALL_BOX = 2;	// Items that are small boxes, dimensions all less than 15cm.
	public static final int MEDIUM_BOX = 3; // Items that are medium boxes, dimensions all less than 30cm, but some greater than 15cm.
	public static final int LARGE_BOX = 4;  // Items that are large boxes, at least one dimensions greater than 30cm
	
	/** Create a new Parcel
	 * @param destinationAddr  The address where to send the parcel to
	 * @param country A specific Country signifying where to send the parcel
	 * @param weight The weight in portions of kilograms
	 * @param isFragile Whether the parcel is fragile, e.g. glass
	 * @param sizeCategory The size of the Parcel (flat is for documents, others are boxes)
	 */
	public Parcel(String destinationAddr, Country country, double weight, boolean isFragile, int sizeCategory)
	{
		// Check the incoming parameters have valid values
		if (destinationAddr == null)
			throw new NullPointerException("Must supply a destination address");
		else if (country == null)
			throw new NullPointerException("Must supply a destination Country");
		else if (weight < 0)
			throw new IllegalArgumentException("weight must be positive, in Constructor for Parcel");
		else if (sizeCategory < 1 || sizeCategory > 4)
			throw new IllegalArgumentException("invalid sizeCategory provided in Constructor for Parcel");
		
		this.destinationAddr = destinationAddr;
		this.countryDest = country;
		this.weight = weight;
		this.sizeCategory = sizeCategory;
		this.isFragile = isFragile;
	}
	
	public String getDestinationAddress()
	{
		return destinationAddr;
	}
	
	public Country getCountryDest()
	{
		return countryDest;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public int getSizeCategory()
	{
		return sizeCategory;
	}
	
	public boolean isFragile()
	{
		return isFragile;
	}
	
	/** Calculates the cost of the parcel based on its setup. */
	public double calculateCost()
	{
		double calculatedCost = 0.0;

		// Fragile items cost $5.15 but non-fragile ones have no extra charge.
		if (isFragile) {
			calculatedCost = 5.15;
		}
		
		// Determine the cost of sending parcel to the relevant country and include it in the total cost
		calculatedCost += countryDest.getCostToShipToCountry();
		
		// Determine cost based on type/size of item and the weight:
		switch(sizeCategory) {
			case FLAT:
				calculatedCost += 2.20 + (0.15 * weight);
				break;
			case SMALL_BOX:
				calculatedCost += 4.65 + (0.30 * weight);
				break;
			case MEDIUM_BOX:
				calculatedCost += 4.65 + (0.77 * weight);
				break;
			case LARGE_BOX:
				calculatedCost += 9.10 + (0.65 * weight);
				break;
		}

		return calculatedCost;
	}
}