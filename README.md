## Project for Mobile Development in Android

#### Projectnaam: Project Calorify

#### Startdatum: 19/09/2017

#### Team: 

- Kenan Ekici

- Pieter-Jan Kerfs

#### Externe API: Nutritionix

#### Branch: Development

## 

### Inleiding

#### Elevator Pitch

Dankzij de kennis die we hebben opgedaan, zoals het ontwikkelen van een mobiele applicatieontwikkeling of het managen van producten in een supermarkt tijdens de vakantiejob, kwamen we op het idee om een applicatie te ontwikkelen rond het scannen van producten en managen van voedingswaarden en allergenen.

Deze applicatie heeft als doel om de gebruiker alle nodige informatie te geven over de voedingswaren die in het product zitten. Deze producten kunnen gescand worden aan de hand van een barcode. De gebruiker kan via een eigen profiel aanmaken en zo aangeven welke allergieën van toepassing zijn. De dagelijkse aanbevolen hoeveelheid calorieën wordt berekend waarmee de gebruiker rekening dient te houden. Indien de gebruiker afwijkt van deze hoeveelheid of indien het product dat men heeft gescand een allergische reactie veroorzaakt, kan de applicatie de gebruiker coachen of indien nodig verwittigen.

**Technisch**

Deze one-tier applicatie maakt gebruik van de Nutritionix API om hier alle informatie over de voedingswaren te halen, deze kunnen we dan via de applicatie zelf verwerken en zo de gebruiker alle nuttige informatie geven. Profielen worden lokaal bijgehouden op het device.


### Doelstellingen (Basic)
	
	Lokaal profiel opstellen
	Producten scannen 
	Gescande product bijhouden in dagelijkse schema
	Voedingswaarde schema berekenen
	Verwittigen op allergenen
	Profielweergave en instellingen

### Doelstellingen (Intermediate)

	Coachen door middel van notificaties
	Eetpatroon berekenen en weergeven

### Doelstellingen (Extra)

	Google nutrition coach API
	Beweging? --> GPS + maps
	Sharen op social accounts

### Authentication

	Geen, maar wel een PIN-code instelbaar

### Environment (Local API)

	Nutritionix API 
	(x-app-id = 4359abb2)
	(x-app-key = 69316a39aa6242115b03d9aa15498b66)

### DATA



### TO-DO (24/10/2017)

Profile fragment
- Avatar
- Chart (weight)

Settings
- Update profile button (weight, height, name, ??)
 
Scan fragment
- Modal bij elk fragment in de scan historiek
- In elk modal kunnen toevoegen aan het scheme.

Scheme fragment
- listviewTextChild veranderen --> Brand name + calorieën
- listviewTextParent veranderen -> Dag + calorieën / dag
- Chart (calories / day)

Algemeen
- Testing environment (is Wifi enabled etc)
- Validation
- Refactoring

##### Extra references:
Outpan API - Gtin voor de basis informatie van het product --> access to Nutritionix API

