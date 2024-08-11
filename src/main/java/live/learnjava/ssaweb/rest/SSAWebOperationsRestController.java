package live.learnjava.ssaweb.rest;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ssa-web-api")
public class SSAWebOperationsRestController {
	private Logger logger = LoggerFactory.getLogger(SSAWebOperationsRestController.class);
	@GetMapping("/find/{ssn}")
	public ResponseEntity<String> getStateBySSN(@PathVariable Integer ssn) {
	    logger.info("*** input SSN *** "+String.valueOf(ssn));
	    
		// check 9 digits
		if (String.valueOf(ssn).length() != 9) {
			return new ResponseEntity<String>("Invalid SSN", HttpStatus.BAD_REQUEST);
		}

		// get state name
		String stateCodeInString = String.format("%02d", Math.abs(ssn % 100));
		int stateCode = Integer.parseInt(stateCodeInString);
		String stateName = null;
		if (stateCode == 01) {
			stateName = "DC";
		} else if (stateCode == 02) {
			stateName = "OH";
		} else if (stateCode == 03) {
			stateName = "TX";
		} else if (stateCode == 04) {
			stateName = "CA";
		} else if (stateCode == 05) {
			stateName = "FL";
		} else {
			stateName = "Invalid SSN";
		}

		return new ResponseEntity<String>(stateName, HttpStatus.OK);

	}
}
