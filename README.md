SU35
====

Fellesprosjekt hovedrepo
________________________

Setup:
	This repo contains source for two projects, both the client and the server.
	You should clone the repo, and set an eclipse project up for both.
	
	To avoid redundancy in the code base, you should make the server project depend on the client project.
	To do this, you need to configure the build path of the server project to include the client project.

	You alse need to add some libraries to the build path, their names and download locations are mentioned below

Required Libraries:
	
	Server:
		mysql-connector-java.5.1.23-bin.jar
			You can get this one from the database example provided on itslearning.