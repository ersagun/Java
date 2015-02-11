package simullc.protocol;

import java.util.*;
import simullc.entity.ProtocolEntity;
import simullc.gui.NodeGraph;
import simullc.gui.Trame;

/*
* Internet Simulator by Madyne
* Copyright (C) 2001 Emmanuel Nataf, Olivier Festor
*               LORIA - Universite de Nancy2 - INRIA
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
*  This library is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
*  Lesser General Public License for more details.
*
*  You should have received a copy of the GNU Lesser General Public
*  License along with this library; if not, write to the Free Software
*  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*
*  Contact : nataf@loria.fr
*            festor@loria.fr
*/
public class ReceiverNoProtocol extends ProtocolEntity {


    public ReceiverNoProtocol(NodeGraph n){
	super(n);
    }

    public void run()
	{
		Trame t = receiveTrame();
		char ch = ' ';
		String str = "";
		display("Message en cours de réception");
		
		while(ch != '#')
		{
			ch = getData(t);
			
			if(ch != '#')
			{
				str += ch;
				writeData(t);
			}
			else display("Message recu");
				
			t = receiveTrame();
		}		
    }
}
