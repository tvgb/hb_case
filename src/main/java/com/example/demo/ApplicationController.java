package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ApplicationController {
	private InMemoryDb db = new InMemoryDb();

	@GetMapping("/lanesoknad")
    public Lanesoknad getLanesoknad(@RequestParam(value = "id") String id) {
		Lanesoknad lanesoknad = db.get(id);

		if (lanesoknad != null)
			return lanesoknad;
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lanesoknad med id " + id + " finnes ikke.");
    }
	
	@GetMapping("/lanesoknad/status")
    public LanesoknadStatus getLanesoknadStatus(@RequestParam(value = "id") String id) {
		Lanesoknad lanesoknad = db.get(id);
		LanesoknadStatus lanesoknadStatus = new LanesoknadStatus();

		if (lanesoknad != null)
			lanesoknadStatus.status = "Motatt";
		else
			lanesoknadStatus.status = "Ukjent";

		return lanesoknadStatus;
    }

	@PostMapping("/lanesoknad")
	public String postLanesoknad(@RequestBody() Lanesoknad lanesoknad) {

		// Sjekk at lånesøknaden ikke er tom
		if (lanesoknad == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lånesøknaden kan ikke være tom.");

		// Sjekk at lånesøknaden inneholder minst én lånetaker
		if (lanesoknad.lanetakere.size() < 1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lånesøknaden må inneholde minst én lånetaker.");
		}
	
		for (Lanetaker lt : lanesoknad.lanetakere) {
			// Sjekk at lånetakeren har et gyldig fødselsnummer
			if (!lt.fnr.matches("^[0-9]{11}$")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lånetakeren med index " + lanesoknad.lanetakere.indexOf(lt) + " har et ugyldig fødselsnummer.");
			}

			if (lt.navn == null || lt.navn.length() < 1) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lånetakeren med index " + lanesoknad.lanetakere.indexOf(lt) + " har et ugyldig navn.");
			}
		}
		
		// Sjekk at lånesøknaden inneholder et gyldig lånebeløp
		if (lanesoknad.lanebelop < 1000) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lånebeløpet må være større enn 1000.0.");
		}

		// Sjekk at lånesøknaden inneholder et gyldig behov
		if (lanesoknad.behov == null || lanesoknad.behov.length() < 1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Behov kan ikke være tomt.");
		}

		String guid = db.add(lanesoknad);
		System.out.println("\n ----- LÅNESØKNAD LAGRET MED ID: " + guid + " -----\n");

		return guid;
	}

	@GetMapping("/lanesoknad/ids")
	public String[] getLaanesoknadIds() {
		return db.getLaanesoknadIds();
	}
}