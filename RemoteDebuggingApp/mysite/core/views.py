# Create your views here.
from django.shortcuts import render
from django.views import View
from .forms import ReviewForm
from django.views.decorators.csrf import ensure_csrf_cookie


class Index(View):
    template = 'index.html'

    def get(self, request):
        return render(request, self.template)


#@ensure_csrf_cookie
def review(request):
    if request.method == 'POST':
        form = ReviewForm(request.POST)
        if form.is_valid():
            text = form.cleaned_data['text']
            with open('core/templates/reviews.txt', 'a') as f:
                f.write(text + '\n')
                f.close()

    form = ReviewForm()
    with open("core/templates/reviews.txt") as f:
        lines = f.readlines()[-2:]
        rev1 = lines[0]
        rev2 = lines[1]
        return render(request, 'index.html', {'form': form, 'rev1': rev1, 'rev2': rev2})
