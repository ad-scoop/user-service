package com.adscoop.userservice.handlers.credit;

import com.adscoop.entiites.CreditInfo;
import com.adscoop.entiites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import org.apache.commons.validator.routines.CreditCardValidator;
import ratpack.exec.ExecutionException;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 10/09/2016.
 */
public class CreateCreditHandler implements Handler {

	private UserNodeServiceImpl userNodeService;
	private CreditCardValidator creditCardValidator;

	@Inject
	public CreateCreditHandler(UserNodeServiceImpl userNodeService, CreditCardValidator creditCardValidator) {

		this.userNodeService = userNodeService;
		this.creditCardValidator = creditCardValidator;
	}

	@Override
	public void handle(Context ctx) throws Exception {
		Long id = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));

		if (id != null) {
			ctx.parse(fromJson(CreditInfo.class)).then(creditInfo -> {
				try {
					UserNode userNode = userNodeService.findbyId(id);

					CreditInfo creditInfo1 = new CreditInfo();
					creditInfo1.setCardHolderName(creditInfo.getCardHolderName());
					if (creditCardValidator.isValid(creditInfo.getCardnumber())) {
						creditInfo1.setCardnumber(creditInfo.getCardnumber());
					} else {
						ctx.getResponse().send("invalid credit card number");
					}
					creditInfo1.setStartDate(creditInfo.getStartDate());
					creditInfo1.setStartEndYear(creditInfo.getStartEndYear());
					creditInfo1.setType(creditInfo.getType());

					userNode.addCreditInfo(creditInfo1);
					userNodeService.saveOrUpdate(userNode);
					ctx.render(json(creditInfo1));
				} catch (ExecutionException e) {
					ctx.getResponse().getStatus().getMessage();
				}

			});
		}

	}
}
