# This program randomly selects a number between one and a thousand and 
# asks the user to guess it. It then tells the user whether the guess 
# was too high or low, and, once the user gets the answer right, it ends
# a message congratulating them. 
# 
# @author John Zeng
# 11/19/2021
    .data 
        askMsg: .asciiz "Please guess a number between 1 and 1000: "
        tooHighMsg: .asciiz "Your guess was too high\n" 
        tooLowMsg: .asciiz "Your guess was too low\n" 
        correctMsg: .asciiz "Your guess is correct! Congratulations!"

    .text 0x00400000
    .globl main 
main:
    li $a1, 1000
    li $v0, 42
    syscall 
    move $t0, $a0   # randomly generate a number and store it in register $t0 

    askGuess: 
        li $v0, 4
        la $a0, askMsg 
        syscall 
        li $v0, 5
        syscall 
        move $t1, $v0       # ask the user to input their guess and move the guess to $t0
    
    bgt $t1, $t0, tooHigh 
    blt $t1, $t0, tooLow
    beq $t1, $t0, correct 

    tooHigh: 
        la $a0, tooHighMsg
        li $v0, 4
        syscall 
        j askGuess
    
    tooLow: 
        la $a0, tooLowMsg
        li $v0, 4
        syscall 
        j askGuess

    correct: 
        la $a0, correctMsg
        li $v0, 4
        syscall 
    
    li $v0, 10
    syscall 