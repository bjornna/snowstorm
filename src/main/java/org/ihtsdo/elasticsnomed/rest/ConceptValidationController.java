package org.ihtsdo.elasticsnomed.rest;

import com.fasterxml.jackson.annotation.JsonView;
import io.kaicode.rest.util.branchpathrewrite.BranchPathUriUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ihtsdo.drools.response.InvalidContent;
import org.ihtsdo.elasticsnomed.domain.Concept;
import org.ihtsdo.elasticsnomed.validation.DroolsValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ConceptValidationController {

	@Autowired
	private DroolsValidationService validationService;

	@ResponseBody
	@RequestMapping(value = "/browser/{branch}/validate/concept", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation("Validation using the Snomed-Drools project.")
	public List<InvalidContent> validateConcept(@ApiParam(value="The branch path") @PathVariable(value="branch") @NotNull final String branchPath,
												@ApiParam(value="The concept to validate") @RequestBody Concept concept) {

		String branchPath1 = BranchPathUriUtil.parseBranchPath(branchPath);
		return validationService.validateConcept(branchPath1, concept);
	}

}