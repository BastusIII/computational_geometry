% Einlesen des Polygons.
poly = importdata('./polygon.txt');


A = zeros(size(poly,1), 3); %Koeffitientenmatrix für Randbedingungen.
b = zeros(size(poly,1),1); %Ergebnisvektor für Randbedingungen.
cw_check = 0; %Variable für Drehrichtungstest.

for i = 1:size(poly, 1)
    % Indizes aufeinadnderfolgender Punkten berechnen.
    j = i+1;
    j(j==size(poly,1)+1) = 1;
    % Zuweisung der zwei Punkte der Linie.
    p1 = [poly(i,1), poly(i,2)];
    p2 = [poly(j,1), poly(j,2)];
    % Raus hier, wenn Endpunkt gleich Startpunkte.
    if p1==p2
        break
    end
    % Berechnung des Normierungsfaktors für den Normalenvektor.
    norm = sqrt((p1(1)-p2(1))^2 + (p1(2)-p2(2))^2);
    % Befüllen einer Zeile der Koeffizientenmatrix.
    A(i,1)=(p1(2) - p2(2))/norm;
    A(i,2)=(p2(1) - p1(1))/norm;
    A(i,3)=1;
    % Befüllen des Koeffizientenvektors.
    b(i)=(p1(2)*p2(1) - p1(1)*p2(2))/norm;
    
    % Addieren der doppelten Fläche des Trapenzes zur Gesamtdoppelfläche.
    cw_check = cw_check + (p2(1)-p1(1)) * (p1(2)+p2(2));
end

% Neue Darstellung.
fig = figure();
% Damit runde Kreise auch rund werden.
daspect([1, 1, 1]);
hold on;
% Zeichnen des Polygons.
fill(poly(:,1), poly(:,2), 'r')

% Negieren der Koeffizienten für CCW-Polygone.
if cw_check < 0
    disp('ccw')
    A(:,1) = -A(:,1);
    A(:,2) = -A(:,2);
    b = -b;
else
    disp('cw')
end

% Zielfunktionskoeffizienten.
c = [0,0,-1];
% Arbeiten lassen!
result = linprog(c,A,b)
% Einzeichnen des gefundenen Kreises.
plotcircle(result(1), result(2), result(3), 'g')
hold off
