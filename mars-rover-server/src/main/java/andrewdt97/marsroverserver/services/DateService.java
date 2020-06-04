package andrewdt97.marsroverserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author andrewdt97
 * Class for working with date strings
 */
@Component
public class DateService {
    private final Logger logger = LoggerFactory.getLogger( DateService.class );
    private SimpleDateFormat dateFormater = new SimpleDateFormat( "yyyy-MM-dd" );
    List<String> possibleFormats = Arrays.asList( "MM/dd/yy", "MMM d, yyyy", "MMM-d-yyyy", "yyyy-MM-dd" );

    /**
     * @author: andrewdt97
     * Ensures a date string is not empty and is in the right format
     * 
     * @param dateStr the string sanitize
     * 
     * @return a date string in the class-specified format
     */
    public String santizeDate( String dateStr ) {
        // Assert string is not empty
        if (dateStr.trim().equals( "" )) {
            StringBuilder errorMessage = new StringBuilder( getClass().toString() );
            errorMessage.append( ", sanitizeData()" )
                .append( " - Attempted to sanitize an empty string." );
            logger.error( errorMessage.toString() );

            throw new NullPointerException( "Date is empty" );
        }

        // Parse into desired format
        Date dateObj = tryParse( dateStr );
        if (dateObj == null) {
            StringBuilder errorMessage = new StringBuilder( getClass().toString() );
            errorMessage.append( ", sanitizeData()" )
                .append( " - Attempted to sanitize invalid date format." );
            logger.error( errorMessage.toString() );
        }

        return dateFormater.format( dateObj );
    }

    /**
     * @author: matt-ball
     * Parses date strings with multiple formats
     * Taken from SO: https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
     * 
     * @param dateString the string to attempt to parse
     * 
     * @return a date string in the class-specified format
     * @throws NumberFortmatException when attempting to parse a date that doesn't meet one of the class-specified read patterns
     */
    private Date tryParse( String dateString ) {
        for (String formatString : possibleFormats) {
            SimpleDateFormat formatter = new SimpleDateFormat( formatString );
            try {
                formatter.setLenient( false );  // Prevents invalid dates with valid formats
                return formatter.parse( dateString );
            } catch (ParseException e) {}
        }

        throw new NumberFormatException( "Tried to parse an invalid date format." );
    }
}