# This program asks the user to input two numbers 
# and outputs the product of those two numbers. 
#
# @author John Zeng
# 11/19/2021
    .data 
        msg: .asciiz "Please enter a number."
    
    .text 0x00400000
    .globl main
main: 
    li $v0, 4
    la $a0, msg        # Ask the user to input a number 
    syscall 
    li $v0, 5  
    syscall         
    move $t1, $v0      # Read the number and store it 

    li $v0, 4
    syscall 
    li $v0, 5
    syscall 
    move $t2, $v0      # Do the same thing again

    mult $t2, $t1      # Multiply the values stored in $t2 and $t1 and 
    mflo $a0           # store the value in $a0

    li $v0, 1
    syscall 
    li $v0, 10
    syscall            # Print the product and terminate the program


