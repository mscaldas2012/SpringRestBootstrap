package edu.msc.opennlp;

/**
 * Created by marcelo on 1/7/17.
 */
public  class  PenTreeBankDef {

    public enum PenTreeBankDefList {
        CC,	CD,	DT, EX,	FW, IN, JJ, JJR, JJS, LS, MD, NN, NNS, NNP, NNPS, PDT, POS, PRP, PRP$, RB, RBR, RBS, RP,
        SYM, TO, UH, VB, VBD, VBG, VBN, VBP, VBZ, WDT, WP, WP$, WRB
    }

    public static String getDescription(String tag) {
        try {
            return getDescription(PenTreeBankDefList.valueOf(tag));
        } catch (IllegalArgumentException e) {
            return "Punctionation -> " + tag;
        }
    }

    public static String getDescription(PenTreeBankDefList tag) {

        switch (tag) {
            case CC:
                return "Coordinating conjunction";
            case CD:
                return "Cardinal number";
            case DT:
                return "Determiner";
            case EX:
                return "Existential there";
            case FW:
                return "Foreign word";
            case IN:
                return "Preposition or subordinating conjunct";
            case JJ:
                return "Adjective";
            case JJR:
                return "Adjective, comparative";
            case JJS:
                return "Adjective, superlative";
            case LS:
                return "List item marker";
            case MD:
                return "Modal";
            case NN:
                return "Noun, singular or mass";
            case NNS:
                return "Noun, plural";
            case NNP:
                return "Proper noun, singular";
            case NNPS:
                return "Proper noun, plural";
            case PDT:
                return "Predeterminer";
            case POS:
                return "Possessive ending";
            case PRP:
                return "Personal pronoun";
            case PRP$:
                return "Possessive pronoun";
            case RB:
                return "Adverb";
            case RBR:
                return "Adverb, comparative";
            case RBS:
                return "Adverb, superlative";
            case RP:
                return "Particle";
            case SYM:
                return "Symbol";
            case TO:
                return "to";
            case UH:
                return "Interjection";
            case VB:
                return "Verb, base form";
            case VBD:
                return "Verb, past tense";
            case VBG:
                return "Verb, gerund or present participle";
            case VBN:
                return "Verb, past participle";
            case VBP:
                return "Verb, non-3rd person singular present";
            case VBZ:
                return "Verb, 3rd person singular present";
            case WDT:
                return "Wh-determiner";
            case WP:
                return "Wh-pronoun";
            case WP$:
                return "Possessive wh-pronoun";
            case WRB:
                return "Wh-adverb";
            default:
                return null;

        }
    }


}
