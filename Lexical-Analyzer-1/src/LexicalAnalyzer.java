
import java.util.ArrayList;
import java.util.logging.Logger;

/*
 * LexicalAnalyzer:
 * 1. Legge l'input inviatogli dal tester getToken(String lessema)
 * 2. Contiene tutti i metodi per riconoscere i lessemi
 * 3. Prepara il token 
 * 4. Lo invia al Tester
 * 
 * Riconoscitore:
 * " "Space
 * "\t"Tab
 * "\f"Invio
 * 
 */
public class LexicalAnalyzer {
	public final static Logger log = Logger.getLogger(LexicalAnalyzer.class.getName());
	public static String ritorna;
	public static String token = "";
	public static int lTesto = 0;
	public static ArrayList <Token> tokens = new ArrayList<Token>();
	public static ArrayList <Token> tabellasimboli = new ArrayList<Token>();
	public static int i = 0;
	public static int id = 1;

	public static ArrayList getToken(String lessema){
		lTesto = lessema.length()-1;
		System.out.println(lTesto);
		while(i<lTesto) {
			System.out.println("Chiamo token");
			token = findToken(lessema);
			token = "";
		}

		return tokens;
	}


	/*
	 * Trova il lessema passatogli da readLessema e lo aggiunge all'array
	 */
	public static String findToken(String testo) {
		int stato = 0;
		//int backword = 0;
		char c;
		boolean active = true;
		while(active) {
			switch(stato) {


			/*
			 * CASE 0: IF Riconosci delimitator ELSE go stato 1
			 */
			case 0:
				c = testo.charAt(i);

				//CASE 1 isLetter
				if(Character.isLetter(c)) {
					token = token + c;
					log.info("Case 0: isLetter");
					stato = 1;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 2 isDigit
				} else if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 0: isDigit");
					stato = 2;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 3 isBracket
				}else if(c == '(' | c == ')'| c == '[' | c == ']') {
					token = token + c;
					log.info("Case 0: isDigit");
					stato = 3;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo
					
					//CASE 4 Relop<
				}else if(c == '<') {
					token = token + c;
					log.info("Case 0: isRelop <");
					stato = 4;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 5 Relop>
				}else if(c == '>') {
					token = token + c;
					log.info("Case 0: isRelop >");
					stato = 5;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo
					
					//CASE 6 Relop=
				}else if(c == '=') {
					token = token + c;
					log.info("Case 0: isRelop =");
					stato = 6;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo
					
				} else {
					System.out.println("Case 0 not recognized");
					active = false;
					if(i <lTesto) {
						i++;
						System.out.println("i++");
					}
					break;

				}
				//Controllo lunghezza testo
				break;

				/*
				 * CASE 1: Riconosce isLetter | isDigit else si ferma
				 */
			case 1: 
				Token to = new Token();
				c = testo.charAt(i);
				if((Character.isLetter(c)) | (Character.isDigit(c))) {
					token = token + c;
					log.info("Case 1: isLetter OR isDigit");
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 1: fine lunghezza");
					}
				} else {
					to = CheckIsKeywords(token);
					System.out.println(to);
					if(to.getId() != "null") {
						to.setId(""+id);
						to.setAttribute(token);
					} else {
						tokens.add(to);
					}
					active = false;
					break;
				}
				break;

				/*
				 * CASE 2: Riconosce isDigit else si ferma
				 */
			case 2: 
				Token to1 = new Token();
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 2: isDigit");
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 2: fine lunghezza");
					}
				} else {
					to1.setId("N_Const");
					to1.setAttribute(token);
					tokens.add(to1);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 3: Si ferma a prescindere dall'input
				 */
			case 3:

				active = false;
				break;


				/*
				 * Default: Not WORK
				 */
			default:
				System.out.println("Finish");
				break;
			}
		}
		return token;
	}


	public static Token CheckIsKeywords(String token) {
		Token to = new Token();
		if(token.equals("if")) {
			to.setId("Keywords");
			to.setAttribute("IF");
		} else if(token.equals("then")) {
			to.setId("Keywords");
			to.setAttribute("THEN");
		} else if(token.equals("else")) {
			to.setId("Keywords");
			to.setAttribute("ELSE");
		} else if(token.equals("while")) {
			to.setId("Keywords");
			to.setAttribute("WHILE");
		} else if(token.equals("for")) {
			to.setId("Keywords");
			to.setAttribute("FOR");
		} else {
			log.severe("SEZIONE CheckIsKeywords: NESSUNA KEY TROVATA!");
		}
		return to;
	}


	/*
	 * DELIMITERS AUTOMA
	 */
	public static void Delimiters(char c) {
		int stato = 0;
		if(c == ' ') {
			log.info("Delimiters - Delimiters - Case 0: Space find");
		} else if(c == '\t') {
			log.info("Delimiters - Case 0: Tab find");
		} else if(c == '\n') {
			log.info("Delimiters - Case 0: new line find");
		} else {
			log.info("Delimiters - Case 0: Not recognized");
		} 
	}
}
