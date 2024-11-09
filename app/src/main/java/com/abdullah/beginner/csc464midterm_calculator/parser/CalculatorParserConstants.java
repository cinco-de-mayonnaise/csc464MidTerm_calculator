/* Generated by: ParserGeneratorCC: Do not edit this line. CalculatorParserConstants.java */
package com.abdullah.beginner.csc464midterm_calculator.parser;


/**
 * Token literal values and constants.
 * Generated by com.helger.pgcc.output.java.OtherFilesGenJava#start()
 */
public interface CalculatorParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int NUMBER_FP = 5;
  /** RegularExpression Id. */
  int NUMBER_INT = 6;
  /** RegularExpression Id. */
  int PLUS = 7;
  /** RegularExpression Id. */
  int MINUS = 8;
  /** RegularExpression Id. */
  int MULTIPLY = 9;
  /** RegularExpression Id. */
  int DIVIDE = 10;
  /** RegularExpression Id. */
  int MODULO = 11;
  /** RegularExpression Id. */
  int POWER = 12;
  /** RegularExpression Id. */
  int LPAREN = 13;
  /** RegularExpression Id. */
  int RPAREN = 14;
  /** RegularExpression Id. */
  int FUNCTION_SIN = 15;
  /** RegularExpression Id. */
  int FUNCTION_COS = 16;
  /** RegularExpression Id. */
  int FUNCTION_TAN = 17;
  /** RegularExpression Id. */
  int FUNCTION_ABS = 18;
  /** RegularExpression Id. */
  int FUNCTION_SQRT = 19;
  /** RegularExpression Id. */
  int FUNCTION_EXP = 20;
  /** RegularExpression Id. */
  int FUNCTION_LOG = 21;
  /** RegularExpression Id. */
  int FUNCTION_LN = 22;
  /** RegularExpression Id. */
  int CONST_PI = 23;
  /** RegularExpression Id. */
  int CONST_E = 24;
  /** RegularExpression Id. */
  int DIGITS = 25;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "<NUMBER_FP>",
    "<NUMBER_INT>",
    "\"+\"",
    "\"-\"",
    "\"\\u00d7\"",
    "\"\\u00f7\"",
    "\"%\"",
    "\"^\"",
    "\"(\"",
    "\")\"",
    "\"sin\"",
    "\"cos\"",
    "\"tan\"",
    "\"abs\"",
    "\"sqrt\"",
    "\"exp\"",
    "\"log\"",
    "\"ln\"",
    "\"\\u03c0\"",
    "\"e\"",
    "<DIGITS>",
  };

}