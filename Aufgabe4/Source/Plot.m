data = zeros(3, 3);
for i = 1:3
   for j = 1:3
       data(i, j) = i * j;
   end
end

%figure
%axis([1 3 2 4]);
mesh(data);